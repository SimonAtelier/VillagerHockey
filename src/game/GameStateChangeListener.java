package game;

import game.States.GameState;

public interface GameStateChangeListener {

	public void onGameStateChanged(IGame game, GameState from, GameState to);
	
}
