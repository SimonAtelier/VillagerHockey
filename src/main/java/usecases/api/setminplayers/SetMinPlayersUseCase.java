package usecases.api.setminplayers;

import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetMinPlayersUseCase implements SetMinPlayers {

	private UUID player;
	private String game;
	private String minPlayers;
	private Integer parsedMinPlayers;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;

	@Override
	public void execute(UUID player, String game, String minPlayers, SetMinPlayersResponse response) {
		setPlayer(player);
		setGame(game);
		setMinPlayers(minPlayers);

		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (noSuchGame()) {
			response.onNoSuchGame(game);
			return;
		}

		parseMinPlayers();

		if (minPlayersNotValid()) {
			response.onMinPlayersIsNotAValidNumber(minPlayers);
			return;
		}
		
		setMinPlayersOfGame();
		response.onMinPlayersSuccessfullySet(game, minPlayers);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.SET_MIN_PLAYERS);
	}
	
	private boolean minPlayersNotValid() {
		return parsedMinPlayers == null;
	}
	
	private void parseMinPlayers() {
		try {
			parsedMinPlayers = Integer.parseInt(minPlayers);
		} catch (NumberFormatException e) {
			parsedMinPlayers = null;
		}
	}

	private boolean noSuchGame() {
		return !gameGateway.containsGame(game);
	}
	
	private void setMinPlayersOfGame() {
		Game game = gameGateway.findGameByName(this.game);
		game.setMinimumPlayersToStart(parsedMinPlayers);
	}

	private void setGame(String game) {
		this.game = game;
	}

	private void setMinPlayers(String minPlayers) {
		this.minPlayers = minPlayers;
	}
	
	private void setPlayer(UUID player) {
		this.player = player;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

}
