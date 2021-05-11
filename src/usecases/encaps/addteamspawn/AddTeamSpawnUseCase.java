package usecases.encaps.addteamspawn;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.TeamSpawnsGateway;

public class AddTeamSpawnUseCase implements AddTeamSpawn {

	private AddTeamSpawnRequest request;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	private TeamSpawnsGateway teamSpawnsGateway;

	@Override
	public void execute(AddTeamSpawnRequest request, AddTeamSpawnResponse response) {
		setRequest(request);

		if (noPermission()) {
			response.onNoPermission();
			return;
		}

		if (noSuchGame()) {
			response.onNoSuchGame();
			return;
		}

		if (noSuchTeam()) {
			response.onNoSuchTeam();
			return;
		}

		addTeamSpawn();
		response.onTeamSpawnSuccessfullyAdd(request.getTeam());
	}

	private boolean noSuchTeam() {
		Game game = gameGateway.findGameByName(request.getGame());
		return !game.getTeams().containsTeamWithName(request.getTeam());
	}

	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}

	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.ADD_TEAM_SPAWN);
	}

	private void addTeamSpawn() {
		Location location = createLocationFromRequest(request);
		teamSpawnsGateway.addTeamSpawn(request.getGame(), request.getTeam(), location);
	}

	private Location createLocationFromRequest(AddTeamSpawnRequest request) {
		Location location = new Location();
		location.setX(request.getX());
		location.setY(request.getY());
		location.setZ(request.getZ());
		location.setPitch(request.getPitch());
		location.setYaw(request.getYaw());
		location.setWorld(request.getWorld());
		return location;
	}

	private void setRequest(AddTeamSpawnRequest request) {
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

	@Override
	public void setTeamSpawnsGateway(TeamSpawnsGateway teamSpawnsGateway) {
		this.teamSpawnsGateway = teamSpawnsGateway;
	}

}
