package usecases.achievements.displayachievements;

import java.util.UUID;

import usecases.achievements.displayachievements.DisplayAchievements.DisplayAchievementsRequest;

public class DisplayAchievementsRequestModel implements DisplayAchievementsRequest {

	private UUID uniquePlayerId;

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}
	
}
