package usecases.api.forcestart;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.forcestart.ForceStart.ForceStartResponse;

public class ForceStartCommand extends AbstractCommand {

	public ForceStartCommand(String name) {
		super(name);
	}

	@Override
	public void execute(UUID player, List<String> arguments) {
		ForceStartView view = new ForceStartViewImpl(player);
		ForceStartResponse presenter = new ForceStartPresenter(view);
		ForceStart useCase = new ForceStartUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(player, presenter);
	}
	
	@Override
	public String[] getArgumentLabels() {
		return new String[] {};
	}

}
