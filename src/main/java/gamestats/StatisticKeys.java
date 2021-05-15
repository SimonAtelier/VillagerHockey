package gamestats;

public interface StatisticKeys {

	static final String GAMES_WON = "games_won";
	static final String GAMES_LOST = "games_lost";
	static final String GAMES_DRAW = "games_draw";
	static final String GAMES_PLAYED = "games_played";
	static final String GAMES_WON_TO_ZERO = "games_won_to_zero";
	static final String GOALS_SCORED_SELF = "goals_scored_self";
	static final String PUCK_HITS_CURRENT_GAME = "puck_hits_current";
	static final String PUCK_HITS_TOTAL = "puck_hits_total";
	static final String UNLOCKED_ACHIEVEMENTS = "unlocked_achievements";
	static final String PINATAS_SMASHED = "pinatas_smashed";
	static final String BAD_TRADES = "bad_trades";
	static final String WINNING_STREAK = "winning_streak";
	static final String WINNING_STREAK_LAST = "winning_streak_last";
	static final String WINNING_STREAK_LONGEST = "winning_streak_longest";
	static final String ACHIEVEMENT_POINTS_EARNED = "achievement_points_earned";
	static final String TOTAL_TIME_PLAYED_IN_SECONDS = "total_time_played_in_seconds";
	
	static final String[] KEYS = new String[] {
			GAMES_WON,
			GAMES_LOST,
			GAMES_DRAW,
			GAMES_PLAYED,
			GOALS_SCORED_SELF,
			PUCK_HITS_CURRENT_GAME,
			PUCK_HITS_TOTAL,
			GAMES_WON_TO_ZERO,
			UNLOCKED_ACHIEVEMENTS,
			PINATAS_SMASHED,
			BAD_TRADES,
			WINNING_STREAK,
			ACHIEVEMENT_POINTS_EARNED
	};
	
}
