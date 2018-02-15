package game.States;

import game.Event.GameStateChangeListener;

public interface GameStateContext {

	void addGameStateChangeListener(GameStateChangeListener listener);
	
	void removeGameStateChangeListener(GameStateChangeListener listener);

	GameState getGameState();
	
	void setGameState(GameState gameState);
	
}
