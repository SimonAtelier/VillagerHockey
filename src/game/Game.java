package game;

import java.util.List;
import java.util.UUID;

import entities.JoinSigns;
import entities.Location;
import entities.Teams;
import game.Event.GameListener;
import game.Event.GameStateChangeListener;
import game.Event.TeamSelectListener;
import game.States.GameState;

public interface Game {

	void addGameStateChangeListener(GameStateChangeListener listener);
	
	void removeGameStateChangeListener(GameStateChangeListener listener);
	
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
	
	GameState getGameState();
	
	void setGameState(GameState gameState);
	
	boolean isMaximumAmountOfPlayersReached();
	
	boolean canPlayerJoin(UUID player);
	

	
	void join(UUID player);
	
	void leave(UUID player);
	
	List<UUID> getUniquePlayerIds();
	
	Teams getTeams();
	
	VillagerSpawner getVillagerSpawner();
	
	void selectLowestTeam(UUID player);
	
	boolean isCanMove();
	
	void setCanMove(boolean canMove);
	
	Goal findGoalOfTeam(String team);
	
	void addGoal(Goal goal);
	
	void setVillagerSpawnLocation(Location location);
	
	void onTeamScored(String team);
	
	void removePlayers();
	
	void addTeamSelectListener(TeamSelectListener listener);
	
	void addGameListener(GameListener listener);
	
}
