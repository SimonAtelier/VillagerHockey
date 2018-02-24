package usecases.chatingame;

import java.util.UUID;

import context.Context;
import usecases.chatingame.ChatIngame.ChatIngameRequest;
import usecases.chatingame.ChatIngame.ChatIngameResponse;

public class ChatIngameController {

	public boolean handleChatIngame(UUID player, String message) {
		if (playerIsNotIngame(player))
			return false;
		
		ChatIngameView view = new ChatIngameViewImpl(player);
		ChatIngameResponse presenter = new ChatIngamePresenter(view);
		ChatIngame useCase = new ChatIngameUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(createRequest(player, message), presenter);
		
		return true;
	}
	
	private ChatIngameRequest createRequest(UUID player, String message) {
		ChatIngameRequestModel requestModel = new ChatIngameRequestModel();
		requestModel.setPlayer(player);
		requestModel.setMessage(message);
		return requestModel;
	}
	
	private boolean playerIsNotIngame(UUID player) {
		return !Context.gameGateway.isIngame(player);
	}
	
}
