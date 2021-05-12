package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import context.Context;
import entities.config.WinTitleConfiguration;
import minigame.view.TitleViewModel;
import usecases.encaps.displaywinner.DisplayWinner.DisplayWinnerResponse;

public class DisplayWinnerPresenter implements DisplayWinnerResponse {

	private DisplayWinnerView view;
	private TitleViewModel titleViewModel;

	public DisplayWinnerPresenter(DisplayWinnerView view) {
		this.view = view;
	}

	@Override
	public void presentWinner(List<UUID> viewers, String team) {
		if (!Context.configuration.isWinTitleEnabled())
			return;
		initModel();
		setupWinnerTitle(team);
		view.displayTitle(titleViewModel, viewers);
	}

	@Override
	public void presentDraw(List<UUID> viewers) {
		if (!Context.configuration.isWinTitleEnabled())
			return;
		initModel();
		setupDrawTitle();
		view.displayTitle(titleViewModel, viewers);
	}

	private void initModel() {
		WinTitleConfiguration configuration = Context.configuration;
		titleViewModel = Context.viewFactory.createTitleViewModel();
		titleViewModel.setFadeInTimeInSeconds(configuration.getWinTitleFadeInTimeInSeconds());
		titleViewModel.setFadeOutTimeInSeconds(configuration.getWinTitleFadeOutTimeInSeconds());
		titleViewModel.setStayTimeInSeconds(configuration.getWinTitleStayTimeInSeconds());
	}

	private void setupWinnerTitle(String team) {
		String subtitle = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_SUBTITLE;
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_TEAM_WON_TITLE;
		title = title.replace("$team$", team);
		titleViewModel.setTitle(title);
		titleViewModel.setSubtitle(subtitle);
	}

	private void setupDrawTitle() {
		String subtitle = "";
		String title = DisplayWinnerViewMessages.DISPLAY_WINNER_DRAW_TITLE;
		titleViewModel.setTitle(title);
		titleViewModel.setSubtitle(subtitle);
	}

}
