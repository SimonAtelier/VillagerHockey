package usecases.SetPlayingTime;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface SetPlayingTime {

	void execute(SetPlayingTimeRequest request, SetPlayingTimeResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface SetPlayingTimeRequest {
		
		UUID getPlayer();
		
		String getGame();
		
		String getPlayingTime();
		
	}
	
	public interface SetPlayingTimeResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onPlayingTimeIsNotAValidNumber(String playingTime);
		
		void onPlayingTimeSuccessfullySet(String game, String playingTime);
		
	}
	
}
