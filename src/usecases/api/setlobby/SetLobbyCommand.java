package usecases.api.setlobby;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.setlobby.SetLobby.SetLobbyResponse;

public class SetLobbyCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		SetLobby useCase = new SetLobbyUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		SetLobbyView view = new SetLobbyViewImpl(player);
		SetLobbyResponse presenter = new SetLobbyPresenter(view);
		SetLobbyController controller = new SetLobbyController(useCase, presenter);
		controller.handleRequest(player, arguments);
	}

	@Override
	public String getName() {
		return "setlobby";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] { "game" };
	}

}
