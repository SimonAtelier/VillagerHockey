package usecases.DisplayWinner;

import java.util.List;
import java.util.UUID;

import view.TitleBarView;
import view.impl.TitleBarViewImpl;

public class DisplayWinnerViewImpl implements DisplayWinnerView {

	@Override
	public void displayWinner(List<UUID> viewers, String team) {
		String subtitle = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_SUBTITLE;
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_TITLE;
		title = title.replace("$team$", team);
		TitleBarView view = new TitleBarViewImpl();
		for (UUID viewer : viewers) {
			view.displayTitle(viewer, title, subtitle, 15);
		}
	}

	@Override
	public void displayDraw(List<UUID> viewers) {
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_DRAW_TITLE;
		TitleBarView view = new TitleBarViewImpl();
		for (UUID viewer : viewers) {
			view.displayTitle(viewer, title, "", 15);
		}
	}
	
}
