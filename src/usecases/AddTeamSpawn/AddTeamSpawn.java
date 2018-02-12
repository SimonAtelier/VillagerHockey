package usecases.AddTeamSpawn;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.TeamSpawnsGateway;

public interface AddTeamSpawn {

	void execute(AddTeamSpawnRequest request, AddTeamSpawnResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setTeamSpawnsGateway(TeamSpawnsGateway teamSpawnsGateway);
	
	public interface AddTeamSpawnRequest {
		
		double getX();
		
		double getY();
		
		double getZ();
		
		double getPitch();
		
		double getYaw();
		
		String getWorld();
		
		String getGame();
		
		String getTeam();
		
		UUID getPlayer();
		
	}
	
	public interface AddTeamSpawnResponse {
		
		void onNoSuchGame();
		
		void onNoSuchTeam();
		
		void onTeamSpawnSuccessfullyAdd(String team);
		
		void onNoPermission();
		
	}
	
}
