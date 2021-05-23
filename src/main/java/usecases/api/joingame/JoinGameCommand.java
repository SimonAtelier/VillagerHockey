package usecases.api.joingame;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.api.joingame.JoinGame.JoinGameRequest;
import usecases.api.joingame.JoinGame.JoinGameResponse;

public class JoinGameCommand extends AbstractCommand {

	public JoinGameCommand(String name) {
		super(name);
	}

	@Override
	public void execute(UUID player, List<String> arguments) {
		JoinGameRequest request = createRequest(player, arguments);
		JoinGame useCase = new JoinGameUseCase();
		JoinGameView view = new JoinGameViewImpl(player);
		JoinGameResponse presenter = new JoinGamePresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(request, presenter);
	}
	
	private JoinGameRequest createRequest(UUID player, List<String> arguments) {
		JoinGameRequestModel request = new JoinGameRequestModel();
		request.setUniquePlayerId(player);
		request.setGame(arguments.get(0));
		return request;
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game"};
	}
	
}
