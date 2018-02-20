package usecases.setlobby;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import usecases.setlobby.SetLobby.SetLobbyRequest;
import usecases.setlobby.SetLobby.SetLobbyResponse;

public class SetLobbyController {

	private SetLobby useCase;
	private SetLobbyResponse presenter;

	public SetLobbyController(SetLobby useCase, SetLobbyResponse presenter) {
		this.useCase = useCase;
		this.presenter = presenter;
	}

	public void handleRequest(UUID player, List<String> arguments) {
		Location location = getLocationByUserId(player);
		SetLobbyRequest request = createRequest(player, arguments.get(0), location);
		useCase.execute(request, presenter);
	}

	private Location getLocationByUserId(UUID player) {
		Location location = Context.playerGateway.findLocationOfPlayer(player);
		if (location == null) {
			location = new Location();
		}
		return location;
	}

	private SetLobbyRequest createRequest(UUID player, String game, Location location) {
		SetLobbyRequestModel request = new SetLobbyRequestModel();
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
