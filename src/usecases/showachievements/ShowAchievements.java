package usecases.showachievements;

import java.util.List;
import java.util.UUID;

import achievement.AchievementGateway;

public interface ShowAchievements {

	void execute(ShowAchievementsRequest request, ShowAchievementsResponse response);
	
	void setAchievementGateway(AchievementGateway achievementGateway);
	
	public interface ShowAchievementsRequest {
		
		UUID getPlayer();
		
	}
	
	public interface ShowAchievementsResponse {
		
		void presentAchievements(List<String> achievements);
		
	}
	
}
