package game.states;

import java.util.UUID;

import game.Game;

public class StoppedGameState extends AbstractGameState {

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "Stopped";
	}

}
