package usecases.joingame;

import java.util.List;
import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class JoinGameUseCase implements JoinGame {

	private JoinGameRequest request;
	private JoinGameResponse response;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(JoinGameRequest request, JoinGameResponse response) {
		setRequest(request);
		setResponse(response);
		
		if (playerHasNoPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (playerAlreadyJoinedAGame()) {
			response.onPlayerAlreadyJoinedAGame();
			return;
		}

		if (noSuchGame()) {
			response.onNoSuchGame(request.getGame());
			return;
		}

		if (maximumAmountOfPlayersReached()) {
			response.onMaximumAmountOfPlayersReached();
			return;
		}
		
		if (gameNotAllowsJoin(request.getUniquePlayerId())) {
			response.onPlayerCannotJoin();
			return;
		}

		join();
		notifyPlayers();
	}
	
	private void join() {
		Game game = gameGateway.findGameByName(request.getGame());
		game.join(request.getUniquePlayerId());
	}
	
	private void notifyPlayers() {
		Game game = gameGateway.findGameByName(request.getGame());
		List<UUID> players = game.getUniquePlayerIds();
		String player = getPlayerName(request.getUniquePlayerId());
		response.presentPlayerJoin(players, player);
	}
	
	private boolean maximumAmountOfPlayersReached() {
		Game game = gameGateway.findGameByName(request.getGame());
		return game.isMaximumAmountOfPlayersReached();
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean playerHasNoPermission() {
		return !permissionGateway.hasPermission(request.getUniquePlayerId(), Permissions.JOIN_GAME);
	}
	
	private boolean playerAlreadyJoinedAGame() {
		return gameGateway.isIngame(request.getUniquePlayerId());
	}
	
	private boolean gameNotAllowsJoin(UUID player) {
		Game game = gameGateway.findGameByName(request.getGame());
		return !game.canPlayerJoin(player);
	}
	
	private String getPlayerName(UUID uniquePlayerId) {
		return playerGateway.findPlayerNameById(uniquePlayerId);
	}
	
	private void setRequest(JoinGameRequest request) {
		this.request = request;
	}
	
	private void setResponse(JoinGameResponse response) {
		this.response = response;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

}
