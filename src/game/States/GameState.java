package game.States;

import java.util.UUID;

import game.Game;

public interface GameState {

	void onTick(Game game);
	
	void enterGameState(Game game);
	
	void leaveGameState(Game game);
	
	void transitionToGameState(Game game, GameState gameState);
	
	void onPlayerJoin(Game game, UUID player);
	
	void onPlayerLeave(Game game, UUID player);
	
	boolean canPlayerJoin(Game game, UUID player);
	
	String getName();
	
}
