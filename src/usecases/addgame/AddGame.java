package usecases.addgame;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface AddGame {

	void execute(UUID player, String name, AddGameResponse response);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface AddGameResponse {
		
		void onGameWithNameAlreadyExists(String name);
		
		void onGameSuccessfullyAdded(String name);
		
		void onInvalidName();
		
		void onNoPermission();
		
	}
	
}
