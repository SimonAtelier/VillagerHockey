package usecases.chatingame;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;

public interface ChatIngame {

	void execute(ChatIngameRequest request, ChatIngameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface ChatIngameRequest {
		
		UUID getPlayer();
		
		String getMessage();
		
	}
	
	public interface ChatIngameResponse {
		
		void onNoPermission();
		
		void onPlayerHasNoTeam();
		
		void onChatWithTeam(List<UUID> viewers, String message, String player);
		
		void onChatWithAll(List<UUID> viewers, String message, String player);
		
	}
	
}
