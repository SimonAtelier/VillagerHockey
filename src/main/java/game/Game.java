package game;

import java.util.List;
import java.util.UUID;

import entities.Location;
import entities.Teams;
import game.event.GameChangeSupport;
import game.event.JoinListener;
import game.event.LeaveListener;
import game.event.TeamScoreListener;
import game.event.TeamSelectListener;
import game.hockey.HockeyGame;
import game.states.GameStateContext;

public interface Game extends GameStateContext, HockeyGame {
	
	void tick();
	
	void start();
	
	void stop();
	
	void join(UUID player);
	
	void leave(UUID player);
	
	void leaveAll();
		
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
		
	boolean isMaximumAmountOfPlayersReached();
	
	boolean canPlayerJoin(UUID player);
		
	List<UUID> getUniquePlayerIds();
	
	Teams getTeams();
		
	void selectLowestTeam(UUID player);
	
	GameCycle getGameCycle();
	
	void setGameCycle(GameCycle gameCycle);
	
	void onLoad();
	
	void onUnload();
	
	GameChangeSupport getChangeSupport();
	
}
