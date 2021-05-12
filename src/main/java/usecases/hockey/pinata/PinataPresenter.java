package usecases.hockey.pinata;

import java.util.List;
import java.util.UUID;

import context.Context;
import minigame.view.MessageView;
import minigame.view.TitleView;
import minigame.view.TitleViewModel;
import usecases.hockey.pinata.Pinata.PinataResponse;

public class PinataPresenter implements PinataResponse {

	@Override
	public void onPresent(List<UUID> players) {
		broadcastSpecialRoundStartedTitle(players);
		broadcastSpecialRoundStartedMessage(players);
	}

	private void broadcastSpecialRoundStartedTitle(List<UUID> players) {
		TitleView titleView = Context.viewFactory.createTitleView();
		titleView.setTitleViewModel(createSpecialRoundTitleViewModel());

		for (UUID viewer : players)
			titleView.display(viewer);
	}

	private void broadcastSpecialRoundStartedMessage(List<UUID> players) {
		MessageView messageView = Context.messageView;
		String message = PinataMessages.PINATA_ROUND_STARTED;

		for (UUID viewer : players)
			messageView.displayMessage(viewer, message);
	}

	private TitleViewModel createSpecialRoundTitleViewModel() {
		TitleViewModel titleViewModel = Context.viewFactory.createTitleViewModel();
		titleViewModel.setTitle(PinataMessages.PINATA_TITLE);
		titleViewModel.setSubtitle(PinataMessages.PINATA_SUBTITLE);
		titleViewModel.setFadeInTimeInSeconds(1);
		titleViewModel.setFadeOutTimeInSeconds(1);
		titleViewModel.setStayTimeInSeconds(2);
		return titleViewModel;
	}

}
