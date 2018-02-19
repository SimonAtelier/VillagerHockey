package usecases.PerformAction.BreakJoinSign;

import entities.Location;
import gateways.GameGateway;
import gateways.JoinSignGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class BreakJoinSignUseCase implements BreakJoinSign {

	private BreakJoinSignRequest request;
	private GameGateway gameGateway;
	private PermissionGateway permissionsGateway;
	private JoinSignGateway joinSignGateway;
	
	@Override
	public void execute(BreakJoinSignRequest request, BreakJoinSignResponse response) {
		setRequest(request);
		
		if (notAJoinSign()) {
			return;
		}
		
		if (noPermission()) {
			response.onNoPermission();
			response.onDiscardBreaking();
			return;
		}
				
		if (noSuchGame()) {
			response.onNoSuchGame(request.getSecondLine().trim());
			return;
		}
				
		removeJoinSign();
		response.onSuccessfullyRemoved(request.getSecondLine().trim());
	}
	
	private void removeJoinSign() {
		Location location = createLocationFromRequest();
		joinSignGateway.removeLocation(request.getSecondLine().trim(), location);
	}
	
	private Location createLocationFromRequest() {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setWorld(request.getWorld());
		return location;
	}
	
	private boolean notAJoinSign() {
		return !request.getFirstLine().trim().equals("[vh]");
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getSecondLine().trim());
	}
	
	private boolean noPermission() {
		return !permissionsGateway.hasPermission(request.getPlayer(), Permissions.REMOVE_JOIN_SIGN);
	}
	
	private void setRequest(BreakJoinSignRequest request) {
		this.request = request;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionsGateway = permissionGateway;
	}

	@Override
	public void setJoinSignGateway(JoinSignGateway joinSignGateway) {
		this.joinSignGateway = joinSignGateway;
	}
	
}