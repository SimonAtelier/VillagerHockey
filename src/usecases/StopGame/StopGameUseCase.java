package usecases.StopGame;

import java.util.UUID;

import game.Game;
import game.States.StoppedGameState;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class StopGameUseCase implements StopGame {

	private StopGameRequest request;
	private StopGameResponse response;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(StopGameRequest request, StopGameResponse response) {
		setRequest(request);
		setResponse(response);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (noSuchGame()) {
			response.onNoSuchGame(request.getGame());
			return;
		}
		
		if (alreadyStopped()) {
			response.onAlreadyStopped(request.getGame());
			return;
		}
		
		notifyPlayers();
		leaveAll();
		stop();
		response.onSuccessfullyStopped(request.getGame());
	}
	
	private void notifyPlayers() {
		Game game = gameGateway.findGameByName(request.getGame());
		response.presentStopping(game.getUniquePlayerIds(), request.getGame());
	}
	
	private void leaveAll() {
		Game game = gameGateway.findGameByName(request.getGame());
		for (UUID player : game.getUniquePlayerIds()) {
			game.leave(player);
		}
	}
	
	private void stop() {
		Game game = gameGateway.findGameByName(request.getGame());
		game.getGameState().transitionToGameState(game, new StoppedGameState());
	}
	
	private boolean alreadyStopped() {
		Game game = gameGateway.findGameByName(request.getGame());
		return game.getGameState().getClass() == StoppedGameState.class;
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.STOP_GAME);
	}
	
	private void setRequest(StopGameRequest request) {
		this.request = request;
	}
	
	private void setResponse(StopGameResponse response) {
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

}
