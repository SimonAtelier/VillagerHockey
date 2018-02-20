package game.countdown.game;

import java.util.List;
import java.util.UUID;

import view.actionbar.ActionBarView;
import view.actionbar.ActionBarViewImpl;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;

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
		TitleViewModel model = titleView.getTitleViewModel();
		model.setTitle(minutes + " Minuten");
		model.setSubtitle("bis Spielende");
		model.setTitleFadeInTimeInSeconds(1);
		model.setTitleFadeOutTimeInSeconds(1);
		model.setTitleStayTimeInSeconds(2);
		model.setSubtitleFadeInTimeInSeconds(1);
		model.setSubtitleFadeOutTimeInSeconds(1);
		model.setSubtitleStayTimeInSeconds(2);
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}
	
}
