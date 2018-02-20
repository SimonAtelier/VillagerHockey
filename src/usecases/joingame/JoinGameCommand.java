package usecases.joingame;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.command.AbstractCommand;
import usecases.joingame.JoinGame.JoinGameRequest;
import usecases.joingame.JoinGame.JoinGameResponse;

public class JoinGameCommand extends AbstractCommand {

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
	public String getName() {
		return "join";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game"};
	}
	
}
