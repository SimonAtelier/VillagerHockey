package usecases.api.setlobby;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface SetLobby {

	void execute(SetLobbyRequest request, SetLobbyResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface SetLobbyRequest {

		double getX();
		
		double getY();
		
		double getZ();
		
		double getPitch();
		
		double getYaw();
		
		String getWorld();
		
		String getGame();
		
		UUID getPlayer();
		
	}
	
	public interface SetLobbyResponse {
		
		void onNoGameWithSuchName(String game);
		
		void onNoPermission();
		
		void onLobbySuccessfullySet(String game);
		
	}
	
}
