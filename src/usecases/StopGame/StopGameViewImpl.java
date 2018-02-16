package usecases.StopGame;

import java.util.UUID;

import context.Context;
import view.MessageView;

public class StopGameViewImpl implements StopGameView {

	private UUID viewer;
	
	public StopGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, StopGameViewMessages.STOP_GAME_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = StopGameViewMessages.STOP_GAME_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displayAlreadyStopped(String game) {
		String message = StopGameViewMessages.STOP_GAME_ALREADY_STOPPED;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displaySuccessfullyStopped(String game) {
		String message = StopGameViewMessages.STOP_GAME_SUCCESSFULLY_STOPPED;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

}
