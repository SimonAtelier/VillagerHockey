package usecases.achievements.displayachievements;

import java.util.List;
import java.util.UUID;

import achievements.AchievementSystem;
import gamestats.GameStatisticGateway;
import gateways.PermissionGateway;

public interface DisplayAchievements {

	void execute(DisplayAchievementsRequest request, DisplayAchievementsResponse response);
	
	void setAchievementSystem(AchievementSystem achievementSystem);
	
	void setGameStatisticsGateway(GameStatisticGateway gameStatisticGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface DisplayAchievementsRequest {
		
		UUID getUniquePlayerId();
		
	}
	
	public interface DisplayAchievementsResponse {
		
		void onNoPermission();
		
		void onDisplay(List<AchievementResponseItem> responseItems);
		
	}
	
}
