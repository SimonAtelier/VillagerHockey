package usecases.StartGame;

import java.util.List;
import java.util.UUID;

import command.AbstractVillagerHockeyCommand;
import context.Context;
import usecases.StartGame.StartGame.StartGameRequest;

public class StartGameCommand extends AbstractVillagerHockeyCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		StartGameView view = new StartGameViewImpl(player);
		StartGamePresenter presenter = new StartGamePresenter(view);
		StartGame useCase = new StartGameUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(createRequest(player, arguments), presenter);
	}
	
	private StartGameRequest createRequest(UUID player, List<String> arguments) {
		StartGameRequestModel requestModel = new StartGameRequestModel();
		requestModel.setGame(arguments.get(0));
		requestModel.setPlayer(player);
		return requestModel;
	}

	@Override
	public String getName() {
		return "start";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game"};
	}

}
