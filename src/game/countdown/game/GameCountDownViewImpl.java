package game.countdown.game;

import java.util.List;
import java.util.UUID;

import view.ActionBarView;
import view.impl.ActionBarViewImpl;
import view.title.TitleView;
import view.title.TitleViewImpl;

public class GameCountDownViewImpl implements GameCountDownView {

	private TitleView titleView;
	private ActionBarView actionBarView;
	
	public GameCountDownViewImpl() {
		titleView = new TitleViewImpl();
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
		titleView.setFadeInTimeInSeconds(1);
		titleView.setStayTimeInSeconds(2);
		titleView.setFadeOutTimeInSeconds(1);
		titleView.setTitle(minutes + " Minuten");
		titleView.setSubtitle("bis Spielende");
		for (UUID viewer : viewers) {
			titleView.setViewer(viewer);
			titleView.display();
		}
	}
	
}
