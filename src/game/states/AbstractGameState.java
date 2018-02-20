package game.states;

import java.util.UUID;

import game.Game;

public abstract class AbstractGameState implements GameState {

	@Override
	public void onTick(Game game) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public void enterGameState(Game game) {
		System.out.println("Enter GameState " + getName());
	}

	@Override
	public void leaveGameState(Game game) {
		System.out.println("Leave GameState " + getName());
	}
	
	@Override
	public void transitionToGameState(Game game, GameState gameState) {
		leaveGameState(game);
		gameState.enterGameState(game);
		game.setGameState(gameState);
	}

	@Override
	public String getName() {
		return "Unknown";
	}

	@Override
	public void onPlayerJoin(Game game, UUID player) {
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
	}

	@Override
	public String toString() {
		return getName();
	}
	
}
