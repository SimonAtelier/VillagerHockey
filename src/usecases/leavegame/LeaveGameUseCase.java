package usecases.leavegame;

import java.util.List;
import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PlayerGateway;

public class LeaveGameUseCase implements LeaveGame {

	private UUID uniquePlayerId;
	private List<UUID> players;
	private LeaveGameResponse response;
	private GameGateway gameGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(UUID uniquePlayerId, LeaveGameResponse response) {
		setUniquePlayerId(uniquePlayerId);
		setResponse(response);
		
		if (playerIsNotIngame()) {
			response.onPlayerIsNotIngame();
			return;
		}
		
		findPlayers();
		leave();
		notifyPlayers();
	}

	private void findPlayers() {
		players = gameGateway.findGameOfPlayer(uniquePlayerId).getUniquePlayerIds();
	}
	
	private void leave() {
		Game game = gameGateway.findGameOfPlayer(uniquePlayerId);
		game.leave(uniquePlayerId);
	}
	
	private void notifyPlayers() {
		response.presentPlayerLeave(getPlayers(), getPlayerName(uniquePlayerId));
	}
	
	private List<UUID> getPlayers() {
		return players;
	}
	
	private void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}
	
	private void setResponse(LeaveGameResponse response) {
		this.response = response;
	}
	
	private boolean playerIsNotIngame() {
		return !gameGateway.isIngame(uniquePlayerId);
	}

	private String getPlayerName(UUID uniquePlayerId) {
		return playerGateway.findPlayerNameById(uniquePlayerId);
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

}
