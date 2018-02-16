package usecases.StopGame;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface StopGame {

	void execute(StopGameRequest request, StopGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface StopGameRequest {
		
		UUID getPlayer();
		
		String getGame();
		
	}
	
	public interface StopGameResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onAlreadyStopped(String game);
		
		void onSuccessfullyStopped(String game);
		
		void presentStopping(List<UUID> viewers, String game);
		
	}
	
}
