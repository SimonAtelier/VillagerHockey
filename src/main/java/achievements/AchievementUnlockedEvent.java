package achievements;

import java.util.UUID;

public class AchievementUnlockedEvent {

	private final UUID uniquePlayerId;
	private final Achievement achievement;
	
	public AchievementUnlockedEvent(UUID uniquePlayerId, Achievement achievement) {
		this.uniquePlayerId = uniquePlayerId;
		this.achievement = achievement;
	}
	
	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}
	
	public Achievement getAchievement() {
		return achievement;
	}
	
}
