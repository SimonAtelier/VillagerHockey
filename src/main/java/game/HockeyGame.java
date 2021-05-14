package game;

import entities.Location;
import game.Goal.GoalResponse;

public interface HockeyGame {

	Goal findGoalOfTeam(String team);
	
	void addGoal(Goal goal);
	
	void setVillagerSpawnLocation(Location location);
	
	void onTeamScored(String team, int points);
	
	boolean isCanLeaveVehicle();
	
	void setCanLeaveVehicle(boolean canLeaveVehilce);
	
	void setGoalsEnabled(boolean goalsEnabled);
	
	boolean isGoalsEnabled();
	
	GoalResponse checkGoal();
	
	VillagerSpawner getVillagerSpawner();
	
	String getName();
	
}
