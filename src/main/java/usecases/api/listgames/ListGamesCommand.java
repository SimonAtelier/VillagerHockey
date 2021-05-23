package usecases.api.listgames;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.listgames.ListGames.ListGamesResponse;

public class ListGamesCommand extends AbstractCommand {

	public ListGamesCommand(String name) {
		super(name);
	}

	@Override
	public void execute(UUID player, List<String> arguments) {
		ListGames useCase = new ListGamesUseCase();
		ListGamesView view = new ListGamesViewImpl(player);
		ListGamesResponse presenter = new ListGamesPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(player, presenter);
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {};
	}
	
}
