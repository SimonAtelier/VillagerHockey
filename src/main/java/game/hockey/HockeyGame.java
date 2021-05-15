package game.hockey;

public interface HockeyGame {

	void addGoal(Goal goal);
	
	void onTeamScored(String team, int points);
		
	Goal findGoalOfTeam(String team);
	
}
