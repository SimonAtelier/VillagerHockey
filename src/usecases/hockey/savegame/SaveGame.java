package usecases.hockey.savegame;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface SaveGame {

	void execute(SaveGameRequest request, SaveGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface SaveGameRequest {
		
		UUID getUniquePlayerId();
		
		String getGameName();
		
	}
	
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
		
		void onCannotSaveAmountOfTeamSpawnsIsNotEqual();
		
	}
	
}
