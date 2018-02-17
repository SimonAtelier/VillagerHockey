package usecases.SetPlayingTime;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Command.AbstractCommand;
import usecases.SetPlayingTime.SetPlayingTime.SetPlayingTimeRequest;
import usecases.SetPlayingTime.SetPlayingTime.SetPlayingTimeResponse;

public class SetPlayingTimeCommand extends AbstractCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		SetPlayingTimeRequest request = createRequest(player, arguments);
		SetPlayingTime useCase = new SetPlayingTimeUseCase();
		SetPlayingTimeView view = new SetPlayingTimeViewImpl(player);
		SetPlayingTimeResponse presenter = new SetPlayingTimePresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(request, presenter);
	}
	
	private SetPlayingTimeRequest createRequest(UUID player, List<String> arguments) {
		SetPlayingTimeRequestModel requestModel = new SetPlayingTimeRequestModel();
		requestModel.setPlayer(player);
		requestModel.setGame(arguments.get(0));
		requestModel.setPlayingTime(arguments.get(1));
		return requestModel;
	}

	@Override
	public String getName() {
		return "setplayingtime";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] {"game", "playingtime"};
	}

}
