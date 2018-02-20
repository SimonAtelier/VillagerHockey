package usecases.displaywinner;

import java.util.List;
import java.util.UUID;

import view.title.TitleBarView;
import view.title.TitleBarViewImpl;

public class DisplayWinnerViewImpl implements DisplayWinnerView {

	@Override
	public void displayWinner(List<UUID> viewers, String team) {
		String subtitle = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_SUBTITLE;
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_TITLE;
		title = title.replace("$team$", team);
		TitleBarView view = new TitleBarViewImpl();
		view.setTitle(title);
		view.setSubtitle(subtitle);
		view.setFadeInTimeInSeconds(1);
		view.setStayTimeInSeconds(15);
		view.setFadeOutTimeInSeconds(1);
		for (UUID viewer : viewers) {
			view.setViewer(viewer);
			view.display();
		}
	}

	@Override
	public void displayDraw(List<UUID> viewers) {
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_DRAW_TITLE;
		TitleBarView view = new TitleBarViewImpl();
		view.setTitle(title);
		view.setSubtitle("");
		view.setFadeInTimeInSeconds(1);
		view.setStayTimeInSeconds(15);
		view.setFadeOutTimeInSeconds(1);
		for (UUID viewer : viewers) {
			view.setViewer(viewer);
			view.display();
		}
	}
	
}
