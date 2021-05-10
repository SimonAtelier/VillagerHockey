package achievements;

import gamestats.StatisticKeys;

public class AchievementFactory {
	
	public static Achievement createEz() {
		Achievement achievement = new Achievement(5, "ez", "EZ!", "Join the game for the first time.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_PLAYED, ActivationRule.EQUALS, 0);
		return achievement;
	}

	public static Achievement createFirstContact() {
		Achievement achievement = new Achievement(5, "first-contact", "First Contact", "Hit the puck for the first time.");
		achievement.addAchieveCondition(StatisticKeys.PUCK_HITS_TOTAL, ActivationRule.EQUALS, 1);
		return achievement;
	}
	
	public static Achievement createHeavyHitter() {
		Achievement achievement = new Achievement(5, "heavy-hitter", "Heavy Hitter", "Hit the puck 100 times in a single game.");
		achievement.addAchieveCondition(StatisticKeys.PUCK_HITS_CURRENT_GAME, ActivationRule.GREATER_OR_EQUALS_TO, 100);
		return achievement;
	}
	
	public static Achievement createWinnerWinner() {
		Achievement achievement = new Achievement(5, "winner-winner", "Winner Winner Chicken Dinner!", "Win your first game.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_WON, ActivationRule.EQUALS, 1);
		return achievement;
	}
	
	public static Achievement createWoodenSpoon() {
		Achievement achievement = new Achievement(5, "wooden-spoon", "Wooden Spoon", "Lose a total of 50 games.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_LOST, ActivationRule.EQUALS, 50);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createTheyHadNoChance() {
		Achievement achievement = new Achievement(5, "no-chance", "They had no chance!", "Win a game to zero.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_WON_TO_ZERO, ActivationRule.EQUALS, 1);
		return achievement;
	}
	
	public static Achievement createStrongHoldTierOne() {
		Achievement achievement = new Achievement(5, "strong-hold-tier-one", "Strong Hold I", "Win 20 games to zero.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_WON_TO_ZERO, ActivationRule.GREATER_OR_EQUALS_TO, 20);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createStrongHoldTierTwo() {
		Achievement achievement = new Achievement(5, "strong-hold-tier-two", "Strong Hold II", "Win 40 games to zero.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_WON_TO_ZERO, ActivationRule.GREATER_OR_EQUALS_TO, 40);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createStrongHoldTierThree() {
		Achievement achievement = new Achievement(5, "strong-hold-tier-three", "Strong Hold III", "Win 80 games to zero.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_WON_TO_ZERO, ActivationRule.GREATER_OR_EQUALS_TO, 80);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createAchievementGoalOne() {
		Achievement achievement = new Achievement(5, "achievement-goal-one", "Achievement Goal I", "Unlock 5 achievements.");
		achievement.addAchieveCondition(StatisticKeys.UNLOCKED_ACHIEVEMENTS, ActivationRule.GREATER_OR_EQUALS_TO, 5);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createAchievementGoalTwo() {
		Achievement achievement = new Achievement(5, "achievement-goal-two", "Achievement Goal II", "Unlock 10 achievements.");
		achievement.addAchieveCondition(StatisticKeys.UNLOCKED_ACHIEVEMENTS, ActivationRule.GREATER_OR_EQUALS_TO, 10);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createAchievementGoalThree() {
		Achievement achievement = new Achievement(5, "achievement-goal-three", "Achievement Goal III", "Unlock 10 achievements.");
		achievement.addAchieveCondition(StatisticKeys.UNLOCKED_ACHIEVEMENTS, ActivationRule.GREATER_OR_EQUALS_TO, 20);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createLegend() {
		Achievement achievement = new Achievement(50, "legend", "Legend", "Play a total of 10000 matches.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_PLAYED, ActivationRule.GREATER_OR_EQUALS_TO, 10000);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createExpert() {
		Achievement achievement = new Achievement(15, "expert", "Expert", "Play a total of 500 matches.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_PLAYED, ActivationRule.GREATER_OR_EQUALS_TO, 500);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createBeginner() {
		Achievement achievement = new Achievement(10, "beginner", "Beginner", "Play a total of 100 matches.");
		achievement.addAchieveCondition(StatisticKeys.GAMES_PLAYED, ActivationRule.GREATER_OR_EQUALS_TO, 100);
		achievement.setProgress(true);
		return achievement;
	}
	
	public static Achievement createPoorPig() {
		Achievement achievement = new Achievement(5, "poor-pig", "Poor Pig", "Smash the 'Pig Pinata' during a special round.");
		achievement.addAchieveCondition(StatisticKeys.PINATAS_SMASHED, ActivationRule.GREATER_OR_EQUALS_TO, 1);
		return achievement;
	}
	
	public static Achievement createBadTrade() {
		Achievement achievement = new Achievement(5, "bad-trade", "Bad Trade", "Try to trade.");
		achievement.addAchieveCondition(StatisticKeys.BAD_TRADES, ActivationRule.GREATER_OR_EQUALS_TO, 1);
		return achievement;
	}
	
	public static Achievement createPinataKing() {
		Achievement achievement = new Achievement(10, "pinata-king", "Pinata King", "Smash the 'Pig Pinata' 10 times in total.");
		achievement.addAchieveCondition(StatisticKeys.PINATAS_SMASHED, ActivationRule.GREATER_OR_EQUALS_TO, 10);
		achievement.setProgress(true);
		return achievement;
	}
	
}
