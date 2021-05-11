package game.countdown.respawn;

import java.util.List;
import java.util.UUID;

import view.actionbar.ActionBarView;
import view.actionbar.ActionBarViewImpl;

public class RespawnCountDownViewImpl implements RespawnCountDownView {

	private ActionBarView actionBarView;

	public RespawnCountDownViewImpl() {
		actionBarView = new ActionBarViewImpl();
	}

	@Override
	public void displayRespawnTime(List<UUID> viewers, int respawnTimeInSeconds) {
		for (UUID viewer : viewers) {
			actionBarView.displayMessage(viewer, "Runde beginnt in " + respawnTimeInSeconds + " Sekunden.");
		}
	}

	@Override
	public void displayRespawnTimeOver(List<UUID> viewers, int respawnTimeInSeconds) {
		for (UUID viewer : viewers) {
			actionBarView.displayMessage(viewer, "Runde beginnt!");
		}
	}

}
