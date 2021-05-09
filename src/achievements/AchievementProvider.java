package achievements;

public class AchievementProvider {
	
	private AchievementSystem achievementSystem;
	
	public void registerAchievements(AchievementSystem achievementSystem) {
		setAchievementSystem(achievementSystem);
		
		registerAchievement(AchievementFactory.createFirstContact());
		registerAchievement(AchievementFactory.createHeavyHitter());
		registerAchievement(AchievementFactory.createWinnerWinner());
		registerAchievement(AchievementFactory.createWoodenSpoon());
		registerAchievement(AchievementFactory.createTheyHadNoChance());
		registerAchievement(AchievementFactory.createStrongHoldTierOne());
		registerAchievement(AchievementFactory.createStrongHoldTierTwo());
		registerAchievement(AchievementFactory.createStrongHoldTierThree());
		registerAchievement(AchievementFactory.createAchievementGoalOne());
		registerAchievement(AchievementFactory.createAchievementGoalTwo());
		registerAchievement(AchievementFactory.createAchievementGoalThree());
		registerAchievement(AchievementFactory.createLegend());
		registerAchievement(AchievementFactory.createExpert());
		registerAchievement(AchievementFactory.createEz());
		
//		registerAchievement(10, "winning-streak", "Winning streak", "Win 10 games in a row.");
	}
	
	private void registerAchievement(Achievement achievement) {
		achievementSystem.registerAchievement(achievement);
	}
	
	private void setAchievementSystem(AchievementSystem achievementSystem) {
		this.achievementSystem = achievementSystem;
	}

}
