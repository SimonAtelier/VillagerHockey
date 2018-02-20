package game.states;

import java.util.UUID;

import game.Game;

public abstract class AbstractGameState implements GameState {

	@Override
	public void onTick(Game game) {
	}
	
	@Override
	public void onPlayerJoin(Game game, UUID player) {
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
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
