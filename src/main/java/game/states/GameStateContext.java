package game.states;

import game.event.GameStateChangeListener;

public interface GameStateContext {

	void addGameStateChangeListener(GameStateChangeListener listener);
	
	void removeGameStateChangeListener(GameStateChangeListener listener);

	GameState getGameState();
	
	void setGameState(GameState gameState);
	
}
