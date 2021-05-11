package usecases.hockey.setvillagerspawn;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import usecases.hockey.setvillagerspawn.SetVillagerSpawn.SetVillagerSpawnRequest;
import usecases.hockey.setvillagerspawn.SetVillagerSpawn.SetVillagerSpawnResponse;

public class SetVillagerSpawnController {
	
	private SetVillagerSpawn useCase;
	private SetVillagerSpawnResponse presenter;
	
	public SetVillagerSpawnController(SetVillagerSpawn useCase, SetVillagerSpawnResponse presenter) {
		this.useCase = useCase;
		this.presenter = presenter;
	}
	
	public void handleRequest(UUID player, List<String> arguments) {
		Location location = getLocationByUserId(player);
		SetVillagerSpawnRequest request = createRequest(player, arguments.get(0), location);
		useCase.execute(request, presenter);
	}

	private Location getLocationByUserId(UUID player) {
		Location location = Context.playerGateway.findLocationOfPlayer(player);
		if (location == null) {
			location = new Location();
		}
		return location;
	}
	
	private SetVillagerSpawnRequest createRequest(UUID player, String game, Location location) {
		SetVillagerSpawnRequestModel request = new SetVillagerSpawnRequestModel();
		request.setX(location.getX());
		request.setY(location.getY());
		request.setZ(location.getZ());
		request.setPitch(location.getPitch());
		request.setYaw(location.getYaw());
		request.setGame(game);
		request.setWorld(location.getWorld());
		request.setPlayer(player);
		return request;
	}

}
