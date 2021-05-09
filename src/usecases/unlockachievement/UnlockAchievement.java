package usecases.unlockachievement;

import java.util.UUID;

import achievements.AchievementSystem;
import entities.config.Configuration;
import gamestats.GameStatisticGateway;

public interface UnlockAchievement {

	void execute(UnlockAchievementRequest request, UnlockAchievementResponse response);
	
	void setAchievementSystem(AchievementSystem achievementSystem);
	
	void setConfiguration(Configuration configuration);
	
	void setGameSatisticGateway(GameStatisticGateway gameStatisticGateway);
	
	public interface UnlockAchievementRequest {
		
		UUID getUniquePlayerId();
		
		String getAchievementId();
		
	}
	
	public interface UnlockAchievementResponse {
		
		void onUnlockedAchievement(UnlockAchievementResponseModel responseModel);
		
		void onNoAchievementWithSuchId(String id);
		
		void onAlreadyUnlocked(String id);
		
	}
	
}
