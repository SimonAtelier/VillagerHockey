package game.Event;

import game.Game;
import game.States.GameState;

public interface GameStateChangeListener {

	public void onGameStateChanged(Game game, GameState from, GameState to);
	
}
