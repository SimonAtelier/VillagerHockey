package game;

import entities.JoinSigns;
import entities.Location;
import game.States.GameState;

public interface IGame {

	int getPlayersCount();
	
	String getName();

	void setName(String name);

	int getPlayingTimeInSeconds();

	void setPlayingTimeInSeconds(int playingTimeInSeconds);

	int getMinimumPlayersToStart();

	void setMinimumPlayersToStart(int minimumPlayersToStart);

	Location getLobby();

	void setLobby(Location lobby);

	JoinSigns getJoinSigns();
	
	void setGameState(GameState gameState);
	
}
