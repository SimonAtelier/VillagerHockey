package game.countdown.game;

import java.util.List;
import java.util.UUID;

import view.ActionBarView;
import view.TitleBarView;
import view.impl.ActionBarViewImpl;
import view.impl.TitleBarViewImpl;

public class GameCountDownViewImpl implements GameCountDownView {

	private TitleBarView titleBarView;
	private ActionBarView actionBarView;
	
	public GameCountDownViewImpl() {
		titleBarView = new TitleBarViewImpl();
		actionBarView = new ActionBarViewImpl();
	}
	
	@Override
	public void displayPlayingTimeLeft(List<UUID> viewers, String timeLeft) {
		for (UUID viewer : viewers) {
			actionBarView.displayMessage(viewer, timeLeft);
		}
	}

	@Override
	public void displayMinutesTillEnd(List<UUID> viewers, int minutes) {
		for (UUID viewer : viewers) {
			titleBarView.displayTitle(viewer, minutes + " Minuten", "bis Spielende", 2);
		}
	}
	
}
