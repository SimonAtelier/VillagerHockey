package usecases.displaywinner;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.config.WinTitleConfiguration;
import usecases.displaywinner.DisplayWinner.DisplayWinnerResponse;
import view.title.TitleViewModel;

public class DisplayWinnerPresenter implements DisplayWinnerResponse {

	private DisplayWinnerView view;
	
	public DisplayWinnerPresenter(DisplayWinnerView view) {
		this.view = view;
	}
	
	@Override
	public void presentWinner(List<UUID> viewers, String team) {
		if (!Context.configuration.isWinTitleEnabled())
			return;
		setupTimes();
		setupWinnerTitle(team);
		view.displayTitle(viewers);
	}

	@Override
	public void presentDraw(List<UUID> viewers) {
		if (!Context.configuration.isWinTitleEnabled())
			return;
		setupTimes();
		setupDrawTitle();
		view.displayTitle(viewers);
	}
	
	private void setupTimes() {
		WinTitleConfiguration configuration = Context.configuration;
		TitleViewModel model = view.getTitleViewModel();
		model.setTitleFadeInTimeInSeconds(configuration.getWinTitleFadeInTimeInSeconds());
		model.setTitleFadeOutTimeInSeconds(configuration.getWinTitleFadeOutTimeInSeconds());
		model.setTitleStayTimeInSeconds(configuration.getWinTitleStayTimeInSeconds());
	}
	
	private void setupWinnerTitle(String team) {
		String subtitle = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_SUBTITLE;
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_TITLE;
		title = title.replace("$team$", team);
		TitleViewModel model = view.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
	}
	
	private void setupDrawTitle() {
		String subtitle = "";
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_DRAW_TITLE;
		TitleViewModel model = view.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
	}

}
