package achievements;

import java.io.File;
import java.io.IOException;

import prototype.AchievementsConfig;

public class AchievementProvider {
	
	private AchievementSystem achievementSystem;
	
	public void registerAchievements(AchievementSystem achievementSystem) {
		setAchievementSystem(achievementSystem);
		
		try {
			File file = new File("plugins/VillagerHockey/achievements.json");
			AchievementsConfig achievementConfig = new AchievementsConfig(file);
			for (Achievement achievement : achievementConfig.getAchievements()) {
				registerAchievement(achievement);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void registerAchievement(Achievement achievement) {
		achievementSystem.registerAchievement(achievement);
	}
	
	private void setAchievementSystem(AchievementSystem achievementSystem) {
		this.achievementSystem = achievementSystem;
	}

}
