package usecases.AddGame;

import java.util.List;
import java.util.UUID;

import command.AbstractVillagerHockeyCommand;
import context.Context;
import usecases.AddGame.AddGame.AddGameResponse;

public class AddGameCommand extends AbstractVillagerHockeyCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		AddGame useCase = new AddGameUseCase();
		AddGameView view = new AddGameViewImpl(player);
		AddGameResponse presenter = new AddGamePresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(player, arguments.get(0), presenter);
	}

	@Override
	public String getName() {
		return "addgame";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] { "name" };
	}

}
