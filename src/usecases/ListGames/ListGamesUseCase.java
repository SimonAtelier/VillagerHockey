package usecases.ListGames;

import java.util.List;
import java.util.UUID;

import context.Context;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class ListGamesUseCase implements ListGames {

	private UUID player;
	private List<String> games;
	
	@Override
	public void execute(UUID player, ListGamesResponse response) {
		setPlayer(player);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		games = findGames();
		
		if (games.isEmpty()) {
			response.onNoGamesToList();
		} else {
			response.present(games);
		}
	}
	
	private List<String> findGames() {
		GameGateway gameGateway = Context.gameGateway;
		return gameGateway.findAllGameNames();
	}
	
	private boolean noPermission() {
		PermissionGateway permissionGateway = Context.permissionGateway;
		return !permissionGateway.hasPermission(player, Permissions.LIST_GAMES);
	}

	private void setPlayer(UUID player) {
		this.player = player;
	}

}
