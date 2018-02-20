package usecases.startgame;

import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class StartGameUseCase implements StartGame {

	private Game game;
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

		initializeGame();

		if (isAlreadyStarted()) {
			response.onAlreadyStarted(request.getGame());
			return;
		}

		start();
		response.onSuccessfullyStarted(request.getGame());
	}

	private void initializeGame() {
		game = gameGateway.findGameByName(request.getGame());
	}

	private void start() {
		game.start();
	}

	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.START_GAME);
	}

	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}

	private boolean isAlreadyStarted() {
		return game.isStarted();
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
