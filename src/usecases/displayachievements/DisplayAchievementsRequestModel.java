package usecases.displayachievements;

import java.util.UUID;

import usecases.displayachievements.DisplayAchievements.DisplayAchievementsRequest;

public class DisplayAchievementsRequestModel implements DisplayAchievementsRequest {

	private UUID uniquePlayerId;

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}
	
}
