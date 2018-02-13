package usecases.SaveGame;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface SaveGame {

	void execute(UUID player, String game, SaveGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface SaveGameResponse {
		
		void onNoSuchGame(String name);
		
		void onGameSuccessfullySaved();
		
		void onNoPermission();
		
		void onCannotSaveGameNoLobbySet();
		
		void onCannotSaveGameNoVillagerSpawnSet();
		
		void onCannotSavePlayingTimeIsLessOrEqualToZero();
		
		void onCannotSaveNumberOfTeamsIsNotTwo();
		
		void onCannotSaveNotAllGoalsSet();
		
		void onCannotSaveSpawnLocationsMissing();
		
	}
	
}
