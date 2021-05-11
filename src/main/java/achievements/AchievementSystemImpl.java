package achievements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import gamestats.StatsProvider;

public class AchievementSystemImpl implements AchievementSystem {

	private HashMap<UUID, Set<String>> unlockedAchievements;
	private HashMap<String, Achievement> achievements;
	private List<AchievementSystemListener> listeners;

	public AchievementSystemImpl() {
		unlockedAchievements = new HashMap<UUID, Set<String>>();
		achievements = new HashMap<String, Achievement>();
		listeners = new ArrayList<AchievementSystemListener>();
	}

	@Override
	public List<Achievement> update(StatsProvider statsProvider) {
		List<Achievement> canAchieve = new ArrayList<Achievement>();
		for (Achievement achievement : achievements.values()) {
			if (canAchieve(achievement, statsProvider)
					&& !isUnlockedForPlayer(achievement.getId(), statsProvider.getUniquePlayerId()))
				canAchieve.add(achievement);
		}
		return canAchieve;
	}

	private boolean canAchieve(Achievement achievement, StatsProvider statsProvider) {
		for (AchieveCondition condition : achievement.getAchieveConditions()) {
			if (!condition.isActive(statsProvider.getValue(condition.getPropertyKey())))
				return false;
		}
		return true;
	}

	@Override
	public Achievement findAchievementById(String id) {
		return achievements.get(id);
	}

	@Override
	public List<Achievement> findUnlockedAchievementsOfPlayer(UUID uniquePlayerId) {
		Set<String> achievementIds = this.unlockedAchievements.get(uniquePlayerId);

		if (achievementIds == null) {
			return new ArrayList<Achievement>();
		}

		List<Achievement> unlockedAchievements = new ArrayList<Achievement>();

		for (String id : achievementIds) {
			Achievement achievement = achievements.get(id);
			unlockedAchievements.add(achievement);
		}

		return unlockedAchievements;
	}

	@Override
	public List<Achievement> findLockedAchievementsOfPlayer(UUID uniquePlayerId) {
		Set<String> achievementIds = unlockedAchievements.get(uniquePlayerId);

		if (achievementIds == null) {
			return new ArrayList<Achievement>(this.achievements.values());
		}

		List<Achievement> unlockedAchievements = new ArrayList<Achievement>();

		for (Achievement achievement : this.achievements.values()) {
			String id = achievement.getId();
			if (!achievementIds.contains(id)) {
				unlockedAchievements.add(achievement);
			}
		}

		return unlockedAchievements;
	}

	@Override
	public List<Achievement> findAllAchievements() {
		ArrayList<Achievement> achievements = new ArrayList<Achievement>(this.achievements.values());
		return achievements;
	}

	@Override
	public long getTotalAchievementPointsOfPlayer(UUID uniquePlayerId) {
		Set<String> achievementIds = this.unlockedAchievements.get(uniquePlayerId);

		if (achievementIds == null) {
			return 0;
		}

		int achievementPoints = 0;

		for (String id : achievementIds) {
			Achievement achievement = achievements.get(id);
			achievementPoints += achievement.getPoints();
		}

		return achievementPoints;
	}

	@Override
	public boolean isUnlockedForPlayer(String achievementId, UUID uniquePlayerId) {
		Set<String> achievementIds = unlockedAchievements.get(uniquePlayerId);

		if (achievementIds == null)
			return false;

		return achievementIds.contains(achievementId);
	}

	@Override
	public void registerAchievement(Achievement achievement) {
		achievements.put(achievement.getId(), achievement);
	}

	@Override
	public int getAchievementCount() {
		return achievements.size();
	}

	@Override
	public long getSumOfPossibleAchievementPoints() {
		int sum = 0;

		for (Achievement achievement : achievements.values()) {
			sum += achievement.getPoints();
		}

		return sum;
	}

	@Override
	public void unlockAchievementForPlayer(UUID uniquePlayerId, String id) {
		Set<String> achievementIds = unlockedAchievements.get(uniquePlayerId);

		if (achievementIds == null) {
			achievementIds = new HashSet<String>();
			unlockedAchievements.put(uniquePlayerId, achievementIds);
		}

		achievementIds.add(id);
	}

	@Override
	public void addAchievementSystemListener(AchievementSystemListener listener) {
		if (listener == null)
			return;
		listeners.add(listener);
	}

	@Override
	public void removeAchievementSystemListener(AchievementSystemListener listener) {
		if (listener == null)
			return;
		listeners.remove(listener);
	}

	@Override
	public void applyProvider(AchievementProvider provider) {
		provider.registerAchievements(this);
	}

}
