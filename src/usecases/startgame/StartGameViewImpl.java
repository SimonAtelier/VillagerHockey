package usecases.startgame;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class StartGameViewImpl implements StartGameView {

	private UUID viewer;
	
	public StartGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, StartGameViewMessages.START_GAME_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = StartGameViewMessages.START_GAME_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displayAlreadyStarted(String game) {
		String message = StartGameViewMessages.START_GAME_ALREADY_STARTED;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displaySuccessfullyStarted(String game) {
		String message = StartGameViewMessages.START_GAME_SUCCESSFULLY_STARTED;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

}
