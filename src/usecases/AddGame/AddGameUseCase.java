package usecases.AddGame;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class AddGameUseCase implements AddGame {

	private String name;
	private UUID player;
	private PermissionGateway permissionGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(UUID player, String name, AddGameResponse response) {
		setRequest(player, name);

		if (playerHasNoPermission()) {
			response.onNoPermission();
			return;
		}

		if (isNameInvalid()) {
			response.onInvalidName();
			return;
		}

		if (gameAlreadyExists()) {
			response.onGameWithNameAlreadyExists(name);
			return;
		}

		if (addGame()) {
			response.onGameSuccessfullyAdded(name);
			return;
		}

		response.onError();
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

	private boolean addGame() {
		return gameGateway.addGame(name);
	}

	private void setRequest(UUID player, String name) {
		this.player = player;
		this.name = name;
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
