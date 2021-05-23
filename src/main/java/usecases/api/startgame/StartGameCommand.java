package usecases.api.startgame;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.startgame.StartGame.StartGameRequest;

public class StartGameCommand extends AbstractCommand {

	public StartGameCommand(String name) {
		super(name);
	}

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
	public String[] getArgumentLabels() {
		return new String[] {"game"};
	}

}
