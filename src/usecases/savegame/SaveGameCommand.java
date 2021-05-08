package usecases.savegame;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.savegame.SaveGame.SaveGameResponse;

public class SaveGameCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		SaveGame useCase = new SaveGameUseCase();
		SaveGameView view = new SaveGameViewImpl(player);
		SaveGameResponse presenter = new SaveGamePresenter(view);
		SaveGameRequestModel requestModel = new SaveGameRequestModel();
		requestModel.setUniquePlayerId(player);
		requestModel.setGameName(arguments.get(0));
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(requestModel, presenter);
	}

	@Override
	public String getName() {
		return "save";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game"};
	}
	
}
