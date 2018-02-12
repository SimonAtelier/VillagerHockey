package usecases.SetLobby;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetLobbyUseCase implements SetLobby {
	
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(SetLobbyRequest request, SetLobbyResponse response) {
		if (!permissionGateway.hasPermission(request.getPlayer(), Permissions.SET_LOBBY)) {
			response.onNoPermission();
			return;
		}

		if (!gameGateway.containsGame(request.getGame())) {
			response.onNoGameWithSuchName(request.getGame());
			return;
		}
		
		Game game = gameGateway.findGameByName(request.getGame());
		Location location = createLocationFromRequest(request);
		game.setLobby(location);
		
		response.onLobbySuccessfullySet(request.getGame());
	}
	
	private Location createLocationFromRequest(SetLobbyRequest request) {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setPitch(request.getPitch());
		location.setYaw(request.getYaw());
		location.setWorld(request.getWorld());
		return location;
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
