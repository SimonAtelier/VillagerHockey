package usecases.api.addteam;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.addteam.AddTeam.AddTeamRequest;
import usecases.api.addteam.AddTeam.AddTeamResponse;

public class AddTeamCommand extends AbstractCommand {

	public AddTeamCommand(String name) {
		super(name);
	}

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
		return requestModel;
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game", "name", "color"};
	}

}
