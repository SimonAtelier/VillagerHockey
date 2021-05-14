package game.states.base;

import java.util.UUID;

import game.Game;

public abstract class AbstractGameState implements GameState {

	private Game game;
	
	@Override
	public void onTick() {
		// Implementation is left to subclass
	}
	
	@Override
	public void onPlayerJoin(UUID player) {
		// Implementation is left to subclass
	}

	@Override
	public void onPlayerLeave(UUID player) {
		// Implementation is left to subclass
	}
	
	@Override
	public void enterGameState() {
		// Implementation is left to subclass
	}

	@Override
	public void leaveGameState() {
		// Implementation is left to subclass
	}
	
	@Override
	public boolean canPlayerJoin(UUID player) {
		return false;
	}

	@Override
	public void transitionToGameState(GameState gameState) {
		leaveGameState();
		getGame().setGameState(gameState);
		gameState.enterGameState();
	}
	
	@Override
	public Game getGame() {
		return game;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
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
