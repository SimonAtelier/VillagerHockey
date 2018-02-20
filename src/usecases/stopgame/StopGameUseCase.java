package usecases.stopgame;

import java.util.UUID;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class StopGameUseCase implements StopGame {

	private Game game;
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
		
		initializeGame();
		
		if (alreadyStopped()) {
			response.onAlreadyStopped(request.getGame());
			return;
		}
		
		notifyPlayers();
		leaveAll();
		stop();
		response.onSuccessfullyStopped(request.getGame());
	}
	
	private void initializeGame() {
		game = gameGateway.findGameByName(request.getGame());
	}
	
	private void notifyPlayers() {
		response.presentStopping(game.getUniquePlayerIds(), request.getGame());
	}
	
	private void leaveAll() {
		for (UUID player : game.getUniquePlayerIds()) {
			game.leave(player);
		}
	}
	
	private void stop() {
		game.stop();
	}
	
	private boolean alreadyStopped() {
		return !game.isStarted();
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
