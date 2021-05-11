package achievements;

import java.util.List;
import java.util.UUID;

import gamestats.StatsProvider;

public interface AchievementSystem {
	
	List<Achievement> update(StatsProvider statsProvider);
	
	Achievement findAchievementById(String id);
	
	List<Achievement> findUnlockedAchievementsOfPlayer(UUID uniquePlayerId);
	
	List<Achievement> findLockedAchievementsOfPlayer(UUID uniquePlayerId);
	
	List<Achievement> findAllAchievements();
	
	long getTotalAchievementPointsOfPlayer(UUID uniquePlayerId);
	
	boolean isUnlockedForPlayer(String achievementId, UUID uniquePlayerId);
	
	void registerAchievement(Achievement achievement);
	
	int getAchievementCount();
	
	long getSumOfPossibleAchievementPoints();
	
	void unlockAchievementForPlayer(UUID uniquePlayerId, String id);
	
	void addAchievementSystemListener(AchievementSystemListener listener);
	
	void removeAchievementSystemListener(AchievementSystemListener listener);
	
	void applyProvider(AchievementProvider provider);

}
