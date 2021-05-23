package usecases.hockey.setgoal;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import entities.command.AbstractCommand;
import gateways.PlayerGateway;
import usecases.hockey.setgoal.SetGoal.SetGoalRequest;
import usecases.hockey.setgoal.SetGoal.SetGoalResponse;

public class SetGoalCommand extends AbstractCommand {

	public SetGoalCommand(String name) {
		super(name);
	}

	@Override
	public void execute(UUID player, List<String> arguments) {
		SetGoalRequest request = createRequest(player, arguments);
		SetGoal useCase = new SetGoalUseCase();
		SetGoalView view = new SetGoalViewImpl(player);
		SetGoalResponse presenter = new SetGoalPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(request, presenter);
	}
	
	private SetGoalRequest createRequest(UUID player, List<String> arguments) {
		Location location = getLocationOfPlayer(player);
		SetGoalRequestModel request = new SetGoalRequestModel();
		request.setPlayer(player);
		request.setGame(arguments.get(0));
		request.setTeam(arguments.get(1));
		request.setLocationId(arguments.get(2));
		request.setX(location.getX());
		request.setY(location.getY());
		request.setZ(location.getZ());
		request.setWorld(location.getWorld());
		return request;
	}
	
	private Location getLocationOfPlayer(UUID player) {
		PlayerGateway playerGateway = Context.playerGateway;
		return playerGateway.findLocationOfPlayer(player);
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game", "team", "loc"};
	}

}
