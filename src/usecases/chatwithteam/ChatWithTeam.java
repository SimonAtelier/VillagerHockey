package usecases.chatwithteam;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;

public interface ChatWithTeam {

	void execute(ChatWithTeamRequest request, ChatWithTeamResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface ChatWithTeamRequest {
		
		UUID getPlayer();
		
		String getMessage();
		
	}
	
	public interface ChatWithTeamResponse {
		
		void onNoPermission();
		
		void onPlayerHasNoTeam();
		
		void onChatWithTeam(List<UUID> viewers, String message, String player);
		
	}
	
}
