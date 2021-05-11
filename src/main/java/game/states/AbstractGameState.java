package game.states;

import java.util.UUID;

import game.Game;

public abstract class AbstractGameState implements GameState {

	@Override
	public void onTick(Game game) {
		// Implementation is left to subclass
	}
	
	@Override
	public void onPlayerJoin(Game game, UUID player) {
		// Implementation is left to subclass
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
		// Implementation is left to subclass
	}
	
	@Override
	public void enterGameState(Game game) {
		// Implementation is left to subclass
	}

	@Override
	public void leaveGameState(Game game) {
		// Implementation is left to subclass
	}
	
	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public void transitionToGameState(Game game, GameState gameState) {
		leaveGameState(game);
		game.setGameState(gameState);
		gameState.enterGameState(game);
	}

	@Override
	public String getName() {
		return "Unknown";
	}

	@Override
	public String toString() {
		return getName();
	}
	
}
