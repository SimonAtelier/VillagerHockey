package usecases.chatwithteam;

import java.util.UUID;

import context.Context;
import usecases.chatwithteam.ChatWithTeam.ChatWithTeamRequest;
import usecases.chatwithteam.ChatWithTeam.ChatWithTeamResponse;

public class ChatWithTeamController {

	public boolean handleChatWithTeam(UUID player, String message) {
		if (playerIsNotIngame(player))
			return false;
		
		ChatWithTeamView view = new ChatWithTeamViewImpl(player);
		ChatWithTeamResponse presenter = new ChatWithTeamPresenter(view);
		ChatWithTeam useCase = new ChatWithTeamUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(createRequest(player, message), presenter);
		
		return true;
	}
	
	private ChatWithTeamRequest createRequest(UUID player, String message) {
		ChatWithTeamRequestModel requestModel = new ChatWithTeamRequestModel();
		requestModel.setPlayer(player);
		requestModel.setMessage(message);
		return requestModel;
	}
	
	private boolean playerIsNotIngame(UUID player) {
		return !Context.gameGateway.isIngame(player);
	}
	
}
