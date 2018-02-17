package game;

import java.util.List;
import java.util.UUID;

import entities.JoinSigns;
import entities.Location;
import entities.Teams;
import game.Event.JoinListener;
import game.Event.LeaveListener;
import game.Event.TeamScoreListener;
import game.Event.TeamSelectListener;
import game.States.GameStateContext;

public interface Game extends GameStateContext {
	
	void start();
	
	void stop();
	
	void join(UUID player);
	
	void leave(UUID player);
		
	void addJoinListener(JoinListener listener);
	
	void removeJoinListener(JoinListener listener);
	
	void addLeaveListener(LeaveListener listener);
	
	void removeLeaveListener(LeaveListener listener);
	
	void addTeamSelectListener(TeamSelectListener listener);
	
	void removeTeamSelectListener(TeamSelectListener listener);
	
	void addTeamScoreListener(TeamScoreListener listener);
	
	void removeTeamScoreListener(TeamScoreListener listener);
	
	boolean isStarted();
	
	int getPlayersCount();
	
	String getName();

	void setName(String name);

	int getPlayingTimeInSeconds();

	void setPlayingTimeInSeconds(int playingTimeInSeconds);

	int getMinimumPlayersToStart();

	void setMinimumPlayersToStart(int minimumPlayersToStart);
	
	int getMaximumAmountOfPlayers();

	Location getLobby();

	void setLobby(Location lobby);

	JoinSigns getJoinSigns();
		
	boolean isMaximumAmountOfPlayersReached();
	
	boolean canPlayerJoin(UUID player);
	
	
	
	
	List<UUID> getUniquePlayerIds();
	
	Teams getTeams();
	
	VillagerSpawner getVillagerSpawner();
	
	void selectLowestTeam(UUID player);
		
	Goal findGoalOfTeam(String team);
	
	void addGoal(Goal goal);
	
	void setVillagerSpawnLocation(Location location);
	
	void onTeamScored(String team);
	
	void removePlayers();
	
}
