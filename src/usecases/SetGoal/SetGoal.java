package usecases.SetGoal;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface SetGoal {

	void execute(SetGoalRequest request, SetGoalResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface SetGoalRequest {
		
		double getX();
		
		double getY();
		
		double getZ();
		
		String getWorld();
		
		String getGame();
		
		String getTeam();
		
		String getLocationId();
		
		UUID getPlayer();
		
	}
	
	public interface SetGoalResponse {
		
		void onNoSuchGame(String name);
		
		void onNoSuchTeam(String name);
		
		void onInvalidLocationId(String id);
		
		void onNoPermission();
		
		void onGoalLocationSuccessfullySet(String locationId);
		
		void onSetLocationWithIdFirst(String id);
		
	}
	
}
