package game;

import entities.JoinSigns;
import entities.Location;
import game.Event.GameStateChangeListener;
import game.States.GameState;

public interface IGame {

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
	
}
