package usecases.api.setminplayers;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.setminplayers.SetMinPlayers.SetMinPlayersResponse;

public class SetMinPlayersCommand extends AbstractCommand {

	public SetMinPlayersCommand(String name) {
		super(name);
	}
	
	@Override
	public void execute(UUID player, List<String> arguments) {
		SetMinPlayers useCase = new SetMinPlayersUseCase();
		SetMinPlayersView view = new SetMinPlayersViewImpl(player);
		SetMinPlayersResponse presenter = new SetMinPlayersPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(player, arguments.get(0), arguments.get(1), presenter);
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game", "minplayers"};
	}

}
