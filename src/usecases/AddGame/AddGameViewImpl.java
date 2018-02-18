package usecases.AddGame;

import java.util.UUID;

import context.Context;
import view.MessageView;

public class AddGameViewImpl implements AddGameView {

	private UUID viewer;

	public AddGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayGameWithNameAlreadyExists(String name) {
		String message = AddGameViewMessages.ADD_GAME_GAME_ALREADY_EXISTS;
		message = message.replace("$game$", name);
		displayMessage(message);
	}

	@Override
	public void displayGameSuccessfullyAdded(String game) {
		String message = AddGameViewMessages.ADD_GAME_SUCCESSFULLY_ADDED_GAME;
		message = message.replace("$game$", game);
		displayMessage(message);
	}
	
	@Override
	public void displayInvalidName() {
		displayMessage(AddGameViewMessages.ADD_GAME_INVALID_NAME);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(AddGameViewMessages.ADD_GAME_NO_PERMISSION);
	}

}
