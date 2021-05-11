package usecases.achievements.unlockachievement;

import java.util.UUID;

import usecases.achievements.unlockachievement.UnlockAchievement.UnlockAchievementRequest;

public class UnlockAchievementRequestModel implements UnlockAchievementRequest {

	private UUID uniquePlayerId;
	private String achievementId;

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

	public String getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(String achievementId) {
		this.achievementId = achievementId;
	}

}
