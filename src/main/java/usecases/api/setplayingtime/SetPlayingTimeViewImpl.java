package usecases.api.setplayingtime;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class SetPlayingTimeViewImpl implements SetPlayingTimeView {

	private UUID viewer;

	public SetPlayingTimeViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, SetPlayingTimeViewMessages.SET_PLAYING_TIME_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = SetPlayingTimeViewMessages.SET_PLAYING_TIME_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displayPlayingTimeIsNotAValidNumber(String playingTime) {
		String message = SetPlayingTimeViewMessages.SET_PLAYING_TIME_NOT_A_NUMBER;
		message = message.replace("$number$", playingTime);
		displayMessage(viewer, message);
	}

	@Override
	public void displayPlayingTimeSuccessfullySet(String game, String playingTime) {
		String message = SetPlayingTimeViewMessages.SET_PLAYING_TIME_SUCCESS;
		message = message.replace("$game$", game);
		message = message.replace("$number$", playingTime);
		displayMessage(viewer, message);
	}

}
