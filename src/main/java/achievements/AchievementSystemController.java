package achievements;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.Game;
import game.event.JoinListener;
import gamestats.GameStatistic;
import gamestats.PropertyChangeListener;
import gamestats.StatsProvider;
import usecases.achievements.unlockachievement.UnlockAchievementController;

public class AchievementSystemController implements PropertyChangeListener, JoinListener {

	private AchievementSystem achievementSystem;
	
	public AchievementSystemController(AchievementSystem achievementSystem) {
		this.achievementSystem = achievementSystem;
	}
	
	@Override
	public void onPropertyChanged(StatsProvider statsProvider) {
		List<Achievement> canAchieve = achievementSystem.update(statsProvider);
		for (Achievement achievement : canAchieve) {
			new UnlockAchievementController().onUnlockAchievement(statsProvider.getUniquePlayerId(), achievement.getId());
		}
	}

	@Override
	public void onPlayerJoin(Game game, UUID player) {
		GameStatistic statsProvider = Context.gameStatisticGateway.findByPlayerId(player);
		List<Achievement> canAchieve = achievementSystem.update(statsProvider);
		for (Achievement achievement : canAchieve) {
			new UnlockAchievementController().onUnlockAchievement(statsProvider.getUniquePlayerId(), achievement.getId());
		}
	}

}
