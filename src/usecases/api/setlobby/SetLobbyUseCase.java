package usecases.api.setlobby;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetLobbyUseCase implements SetLobby {
	
	private SetLobbyRequest request;
	private SetLobbyResponse response;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(SetLobbyRequest request, SetLobbyResponse response) {
		setRequest(request);
		setResponse(response);
		
		if (noPermission()) {
			sendNoPermissionResponse();
			return;
		}

		if (noSuchGame()) {
			sendNoGameWithSuchNameResponse();
			return;
		}
		
		setLobby();
		sendSuccessResponse();
	}
	
	private void sendNoGameWithSuchNameResponse() {
		getResponse().onNoGameWithSuchName(getGameNameFromRequest());
	}
	
	private void sendSuccessResponse() {
		getResponse().onLobbySuccessfullySet(getGameNameFromRequest());
	}
	
	private void sendNoPermissionResponse() {
		getResponse().onNoPermission();
	}
	
	private Location createLocationFromRequest() {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setPitch(request.getPitch());
		location.setYaw(request.getYaw());
		location.setWorld(request.getWorld());
		return location;
	}
	
	private String getGameNameFromRequest() {
		return getRequest().getGame();
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(getGameNameFromRequest());
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(getRequest().getPlayer(), Permissions.SET_LOBBY);
	}
	
	private void setLobby() {
		Game game = gameGateway.findGameByName(getGameNameFromRequest());
		game.setLobby(createLocationFromRequest());
	}

	private SetLobbyResponse getResponse() {
		return response;
	}

	private void setResponse(SetLobbyResponse response) {
		this.response = response;
	}
	
	private SetLobbyRequest getRequest() {
		return request;
	}
	
	private void setRequest(SetLobbyRequest request) {
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
