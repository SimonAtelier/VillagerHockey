package usecases.StartGame;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface StartGame {

	void execute(StartGameRequest request, StartGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface StartGameRequest {
		
		UUID getPlayer();
		
		String getGame();
		
	}
	
	public interface StartGameResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onAlreadyStarted(String game);
		
		void onSuccessfullyStarted(String game);
		
	}
	
}
