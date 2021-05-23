package usecases.encaps.addteamspawn;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import entities.command.AbstractCommand;
import usecases.encaps.addteamspawn.AddTeamSpawn.AddTeamSpawnRequest;
import usecases.encaps.addteamspawn.AddTeamSpawn.AddTeamSpawnResponse;

public class AddTeamSpawnCommand extends AbstractCommand {

	public AddTeamSpawnCommand(String name) {
		super(name);
	}
	
	@Override
	public void execute(UUID player, List<String> arguments) {
		AddTeamSpawnRequest request = createRequest(player, arguments);
		AddTeamSpawn useCase = new AddTeamSpawnUseCase();
		AddTeamSpawnView view = new AddTeamSpawnViewImpl(player);
		AddTeamSpawnResponse presenter = new AddTeamSpawnPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setTeamSpawnsGateway(Context.teamSpawnsGateway);
		useCase.execute(request, presenter);
	}

	private AddTeamSpawnRequest createRequest(UUID player, List<String> arguments) {
		AddTeamSpawnRequestModel requestModel = new AddTeamSpawnRequestModel();
		Location location = getLocationOfPlayer(player);
		requestModel.setGame(arguments.get(0));
		requestModel.setTeam(arguments.get(1));
		requestModel.setX(location.getX());
		requestModel.setY(location.getY());
		requestModel.setZ(location.getZ());
		requestModel.setPitch(location.getPitch());
		requestModel.setYaw(location.getYaw());
		requestModel.setWorld(location.getWorld());
		requestModel.setPlayer(player);
		return requestModel;
	}

	private Location getLocationOfPlayer(UUID player) {
		return Context.playerGateway.findLocationOfPlayer(player);
	}
	
	@Override
	public String[] getArgumentLabels() {
		return new String[] { "game", "team" };
	}

}
