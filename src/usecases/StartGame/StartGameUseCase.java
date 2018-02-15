package usecases.StartGame;

import game.Game;
import game.States.StoppedGameState;
import game.States.WaitingGameState;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class StartGameUseCase implements StartGame {

	private StartGameRequest request;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(StartGameRequest request, StartGameResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (noSuchGame()) {
			response.onNoSuchGame(request.getGame());
			return;
		}
		
		if(isAlreadyStarted()) {
			response.onAlreadyStarted(request.getGame());
			return;
		}
			
		start();
		response.onSuccessfullyStarted(request.getGame());
	}
	
	private void start() {
		Game game = gameGateway.findGameByName(request.getGame());
		game.getGameState().transitionToGameState(game, new WaitingGameState());
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.START_GAME);
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean isAlreadyStarted() {
		Game game = gameGateway.findGameByName(request.getGame());
		return game.getGameState().getClass() != StoppedGameState.class;
	}
	
	private void setRequest(StartGameRequest request) {
		this.request = request;
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
