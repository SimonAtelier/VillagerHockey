package usecases.forcestart;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface ForceStart {

	void execute(UUID player, ForceStartResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface ForceStartResponse {
	
		void onNoPermission();
		
		void displayForcedStart(String game);
		
	}
	
}
