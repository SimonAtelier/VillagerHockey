package usecases.hockey.setvillagerspawn;

import context.Context;
import entities.Location;
import game.Game;
import game.hockey.HockeyGameCycle;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetVillagerSpawnUseCase implements SetVillagerSpawn {

	private SetVillagerSpawnRequest request;
	
	@Override
	public void execute(SetVillagerSpawnRequest request, SetVillagerSpawnResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (noSuchGame()) {
			response.onNoSuchGame();
			return;
		}
		
		setVillagerSpawnLocation();
		
		response.onVillagerSpawnSuccessfullySet(request.getGame());
	}
	
	private void setVillagerSpawnLocation() {
		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.findGameByName(request.getGame());
		HockeyGameCycle hockey = (HockeyGameCycle) game.getGameCycle();
		hockey.setVillagerSpawnLocation(createLocationFromRequest(request));
	}
	
	private boolean noSuchGame() {
		GameGateway gameGateway = Context.gameGateway;
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean noPermission() {
		PermissionGateway permissionGateway = Context.permissionGateway;
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.SET_VILLAGER_SPAWN);
	}
	
	private void setRequest(SetVillagerSpawnRequest request) {
		this.request = request;
	}
	
	private Location createLocationFromRequest(SetVillagerSpawnRequest request) {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setPitch(request.getPitch());
		location.setYaw(request.getYaw());
		location.setWorld(request.getWorld());
		return location;
	}

}
