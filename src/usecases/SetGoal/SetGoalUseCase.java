package usecases.SetGoal;

import entities.Location;
import entities.Teams;
import game.Game;
import game.Goal;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SetGoalUseCase implements SetGoal {

	private SetGoalRequest request;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;

	@Override
	public void execute(SetGoalRequest request, SetGoalResponse response) {
		this.request = request;

		if (playerHasNoPermission()) {
			response.onNoPermission();
			return;
		}

		if (gameNotExists()) {
			response.onNoSuchGame(request.getGame());
			return;
		}

		if (teamNotExists()) {
			response.onNoSuchTeam(request.getTeam());
			return;
		}

		if (locationIdIsInvalid()) {
			response.onInvalidLocationId(request.getLocationId());
			return;
		}

		Game game = gameGateway.findGameByName(request.getGame());
		
		if (request.getLocationId().equals("loc1")) {
			Goal goal = new Goal(game, request.getTeam());
			game.addGoal(goal);
			goal.setLocationOne(createLocationFromRequest());
		}
		
		if (game.findGoalOfTeam(request.getTeam()) == null) {
			response.onSetLocationWithIdFirst("loc1");
			return;
		}

		if (request.getLocationId().equals("loc2")) {
			Goal goal = game.findGoalOfTeam(request.getTeam());
			goal.setLocationTwo(createLocationFromRequest());
			goal.start();
		}

		response.onGoalLocationSuccessfullySet(request.getLocationId());
	}

	private Location createLocationFromRequest() {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setPitch(0);
		location.setYaw(0);
		location.setWorld(request.getWorld());
		return location;
	}

	private boolean teamNotExists() {
		Game game = gameGateway.findGameByName(request.getGame());
		Teams teams = game.getTeams();
		return !teams.containsTeamWithName(request.getTeam());
	}

	private boolean gameNotExists() {
		return !gameGateway.containsGame(request.getGame());
	}

	private boolean locationIdIsInvalid() {
		return !(request.getLocationId().equals("loc1") || request.getLocationId().equals("loc2"));
	}

	private boolean playerHasNoPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.SET_GOAL);
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
