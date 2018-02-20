package usecases.displaywinner;

import java.util.List;
import java.util.UUID;

import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;

public class DisplayWinnerViewImpl implements DisplayWinnerView {

	@Override
	public void displayWinner(List<UUID> viewers, String team) {
		String subtitle = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_SUBTITLE;
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_TITLE;
		title = title.replace("$team$", team);
		
		TitleView titleView = new TitleViewImpl();
		TitleViewModel model = titleView.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
		model.setTitleFadeInTimeInSeconds(1);
		model.setTitleFadeOutTimeInSeconds(1);
		model.setTitleStayTimeInSeconds(15);
		model.setSubtitleFadeInTimeInSeconds(1);
		model.setSubtitleFadeOutTimeInSeconds(1);
		model.setSubtitleStayTimeInSeconds(15);
		
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}

	@Override
	public void displayDraw(List<UUID> viewers) {
		String subtitle = "";
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_DRAW_TITLE;
		
		TitleView titleView = new TitleViewImpl();
		TitleViewModel model = titleView.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
		model.setTitleFadeInTimeInSeconds(1);
		model.setTitleFadeOutTimeInSeconds(1);
		model.setTitleStayTimeInSeconds(15);
		model.setSubtitleFadeInTimeInSeconds(1);
		model.setSubtitleFadeOutTimeInSeconds(1);
		model.setSubtitleStayTimeInSeconds(15);
		
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}
	
}
