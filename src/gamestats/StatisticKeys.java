package gamestats;

public interface StatisticKeys {

	static final String GAMES_WON = "games-won";
	static final String GAMES_LOST = "games-lost";
	static final String GAMES_DRAW = "games-draw";
	static final String GAMES_PLAYED = "games-played";
	static final String GOALS_SCORED_SELF = "goals-scored-self";
	static final String PUCK_HITS_CURRENT_GAME = "puck-hits-current";
	static final String PUCK_HITS_TOTAL = "puck-hits-total";
	static final String WINS_TO_ZERO = "wins-to-zero";
	static final String UNLOCKED_ACHIEVEMENTS = "unlocked-achievements";
	static final String PINATAS_SMASHED = "pinatas-smashed";
	
	static final String[] KEYS = new String[] {
			GAMES_WON,
			GAMES_LOST,
			GAMES_DRAW,
			GAMES_PLAYED,
			GOALS_SCORED_SELF,
			PUCK_HITS_CURRENT_GAME,
			PUCK_HITS_TOTAL,
			WINS_TO_ZERO,
			UNLOCKED_ACHIEVEMENTS,
			PINATAS_SMASHED
	};
	
}
