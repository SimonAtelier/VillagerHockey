package game.states.base;

import java.util.UUID;

import game.Game;

public interface GameState {

	void onTick();
	
	void enterGameState();
	
	void leaveGameState();
	
	void transitionToGameState(GameState gameState);
	
	void onPlayerJoin(UUID player);
	
	void onPlayerLeave(UUID player);
	
	boolean canPlayerJoin(UUID player);
	
	String getName();
	
	Game getGame();
	
	void setGame(Game game);
	
}
