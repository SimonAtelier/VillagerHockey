package usecases.AddTeam;

import java.util.List;
import java.util.UUID;

import command.AbstractCommand;
import context.Context;
import usecases.AddTeam.AddTeam.AddTeamRequest;
import usecases.AddTeam.AddTeam.AddTeamResponse;

public class AddTeamCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		AddTeamRequest request = createRequest(player, arguments);
		AddTeam useCase = new AddTeamUseCase();
		AddTeamView view = new AddTeamViewImpl(player);
		AddTeamResponse presenter = new AddTeamPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(request, presenter);
	}
	
	private AddTeamRequest createRequest(UUID player, List<String> arguments) {
		AddTeamRequestModel requestModel = new AddTeamRequestModel();
		requestModel.setPlayer(player);
		requestModel.setGame(arguments.get(0));
		requestModel.setName(arguments.get(1));
		requestModel.setColor(arguments.get(2));
		requestModel.setSize(arguments.get(3));
		return requestModel;
	}

	@Override
	public String getName() {
		return "addteam";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game", "name", "color", "size"};
	}

}
