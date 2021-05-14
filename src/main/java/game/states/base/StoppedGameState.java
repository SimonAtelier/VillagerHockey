package game.states.base;

import java.util.UUID;

public class StoppedGameState extends AbstractGameState {

	@Override
	public boolean canPlayerJoin(UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "Stopped";
	}

	@Override
	public void enterGameState() {
		// Do nothing
	}

	@Override
	public void leaveGameState() {
		// Do nothing
	}

}
