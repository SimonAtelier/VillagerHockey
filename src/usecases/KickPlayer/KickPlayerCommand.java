package usecases.KickPlayer;

import java.util.List;
import java.util.UUID;

import command.AbstractVillagerHockeyCommand;
import context.Context;
import usecases.KickPlayer.KickPlayer.KickPlayerRequest;
import usecases.KickPlayer.KickPlayer.KickPlayerResponse;

public class KickPlayerCommand extends AbstractVillagerHockeyCommand {

	@Override
	public void execute(UUID player, List<String> arguments) {
		KickPlayerRequest request = createRequest(player, arguments);
		KickPlayer useCase = new KickPlayerUseCase();
		KickPlayerView view = new KickPlayerViewImpl(player);
		KickPlayerResponse presenter = new KickPlayerPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(request, presenter);
	}

	private KickPlayerRequest createRequest(UUID player, List<String> arguments) {
		KickPlayerRequestModel request = new KickPlayerRequestModel();
		request.setKicker(player);
		request.setPlayerToKick(arguments.get(0));
		request.setKickMessage(arguments.get(1));
		return request;
	}

	@Override
	public String getName() {
		return "kick";
	}

	@Override
	public String[] getArgumentLabels() {
		return new String[] { "player", "reason" };
	}

}
