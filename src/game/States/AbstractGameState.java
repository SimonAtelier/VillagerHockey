package game.States;

import java.util.UUID;

import game.Game;

public abstract class AbstractGameState implements GameState {
	
	@Override
	public void onEnterRespawnPhase(Game game) {
	}

	@Override
	public void onLeaveRespawnPhase(Game game) {
	}

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public void onEnterGameState(Game game) {
		System.out.println("Enter GameState " + getName());
	}

	@Override
	public void onLeaveGameState(Game game) {
		System.out.println("Leave GameState " + getName());
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
