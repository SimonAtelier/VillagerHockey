package game.UseCases.TeleportPlayersToLobby;

import java.util.List;
import java.util.UUID;

import entities.Location;
import gateways.GameGateway;

public class TeleportPlayersToLobbyUseCase implements TeleportPlayersToLobby {

	private String game;
	private Location location;
	private List<UUID> players;
	private GameGateway gameGateway;
	
	@Override
	public void execute(String game, TeleportPlayersToLobbyResponse response) {
		this.game = game;
		findLocation();
		findPlayers();
		response.presentLocation(players, location);
	}
	
	private void findLocation() {
		location = gameGateway.findGameByName(game).getLobby();
	}
	
	private void findPlayers() {
		players = gameGateway.findGameByName(game).getUniquePlayerIds();
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
