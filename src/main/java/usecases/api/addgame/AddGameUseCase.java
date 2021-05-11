package usecases.api.addgame;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class AddGameUseCase implements AddGame {

	private String name;
	private UUID player;
	private AddGameResponse response;
	private PermissionGateway permissionGateway;
	private GameGateway gameGateway;

	@Override
	public void execute(UUID player, String name, AddGameResponse response) {
		setRequest(player, name);
		setResponse(response);

		if (playerHasNoPermission()) {
			sendNoPermissionResponse();
			return;
		}

		if (isNameInvalid()) {
			sendInvalidGameNameResponse();
			return;
		}

		if (gameAlreadyExists()) {
			sendGameWithNameAlreadyExistsResponse();
			return;
		}

		addGame();
		sendSuccessResponse();
	}
	
	private void sendGameWithNameAlreadyExistsResponse() {
		getResponse().onGameWithNameAlreadyExists(name);
	}
	
	private void sendInvalidGameNameResponse() {
		getResponse().onInvalidName();
	}
	
	private void sendNoPermissionResponse() {
		getResponse().onNoPermission();
	}
	
	private void sendSuccessResponse() {
		getResponse().onGameSuccessfullyAdded(name);
	}

	private boolean playerHasNoPermission() {
		return !permissionGateway.hasPermission(player, Permissions.ADD_GAME);
	}

	private boolean isNameInvalid() {
		return name == null || name.trim().isEmpty();
	}

	private boolean gameAlreadyExists() {
		return gameGateway.containsGame(name);
	}

	private void addGame() {
		gameGateway.addGame(name);
	}

	private void setRequest(UUID player, String name) {
		this.player = player;
		this.name = name;
	}
	
	private AddGameResponse getResponse() {
		return response;
	}
	
	private void setResponse(AddGameResponse response) {
		this.response = response;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
