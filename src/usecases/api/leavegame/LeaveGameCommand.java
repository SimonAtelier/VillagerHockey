package usecases.api.leavegame;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.leavegame.LeaveGame.LeaveGameResponse;

public class LeaveGameCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		LeaveGame useCase = new LeaveGameUseCase();
		LeaveGameView view = new LeaveGameViewImpl(player);
		LeaveGameResponse presenter = new LeaveGamePresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(player, presenter);
	}

	@Override
	public String getName() {
		return "leave";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {};
	}
	
}
