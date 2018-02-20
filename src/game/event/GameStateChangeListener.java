package game.event;

import game.Game;
import game.states.GameState;

public interface GameStateChangeListener {

	void onGameStateChanged(Game game, GameState from, GameState to);
	
}
