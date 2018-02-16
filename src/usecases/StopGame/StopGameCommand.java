package usecases.StopGame;

import java.util.List;
import java.util.UUID;

import command.AbstractVillagerHockeyCommand;
import context.Context;
import usecases.StopGame.StopGame.StopGameRequest;
import usecases.StopGame.StopGame.StopGameResponse;

public class StopGameCommand extends AbstractVillagerHockeyCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		StopGameView view = new StopGameViewImpl(player);
		StopGameResponse presenter = new StopGamePresenter(view);
		StopGame useCase = new StopGameUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(createRequest(player, arguments), presenter);
	}
	
	private StopGameRequest createRequest(UUID player, List<String> arguments) {
		StopGameRequestModel requestModel = new StopGameRequestModel();
		requestModel.setPlayer(player);
		requestModel.setGame(arguments.get(0));
		return requestModel;
	}

	@Override
	public String getName() {
		return "stop";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game"};
	}

}
