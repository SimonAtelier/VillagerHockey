package game.Event;

import game.IGame;
import game.States.GameState;

public interface GameStateChangeListener {

	public void onGameStateChanged(IGame game, GameState from, GameState to);
	
}
