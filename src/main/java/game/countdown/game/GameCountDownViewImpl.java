package game.countdown.game;

import java.util.List;
import java.util.UUID;

import context.Context;
import minigame.view.ActionBarView;
import minigame.view.TitleView;
import minigame.view.TitleViewModel;

public class GameCountDownViewImpl implements GameCountDownView {

	private TitleView titleView;
	private ActionBarView actionBarView;
	
	public GameCountDownViewImpl() {
		titleView = Context.viewFactory.createTitleView();
		actionBarView = Context.viewFactory.createActionBarView();
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
		model.setFadeInTimeInSeconds(1);
		model.setFadeOutTimeInSeconds(1);
		model.setStayTimeInSeconds(2);
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}
	
}
