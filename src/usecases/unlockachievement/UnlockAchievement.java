package usecases.unlockachievement;

import java.util.UUID;

import achievements.AchievementSystem;

public interface UnlockAchievement {

	void execute(UnlockAchievementRequest request, UnlockAchievementResponse response);
	
	void setAchievementSystem(AchievementSystem achievementSystem);
	
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
