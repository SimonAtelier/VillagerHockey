package usecases.api.kickplayer;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;

public interface KickPlayer {

	public void execute(KickPlayerRequest request, KickPlayerResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface KickPlayerRequest {
		
		String getPlayerToKick();
		
		String getKickMessage();
		
		UUID getKicker();
		
	}
	
	public interface KickPlayerResponse {
	
		void onPlayerWithSuchNameNotFound(String name);
		
		void onPlayerIsNotIngame();
		
		void onPlayerSuccessfullyKicked();
		
		void onNoPermission();
		
		void presentKickMessage(UUID receiver, String message);
		
		void presentPlayerKicked(List<UUID> players, String kickedPlayer);
		
	}
	
}
