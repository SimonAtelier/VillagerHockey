package usecases.setlobby;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetLobbyUseCase implements SetLobby {
	
	private SetLobbyRequest request;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(SetLobbyRequest request, SetLobbyResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}

		if (noSuchGame()) {
			response.onNoGameWithSuchName(request.getGame());
			return;
		}
		
		setLobby();
		
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
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.SET_LOBBY);
	}
	
	private void setRequest(SetLobbyRequest request) {
		this.request = request;
	}
	
	private void setLobby() {
		Game game = gameGateway.findGameByName(request.getGame());
		game.setLobby(createLocationFromRequest(request));
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
