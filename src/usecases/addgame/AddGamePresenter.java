package usecases.addgame;

import usecases.addgame.AddGame.AddGameResponse;

public class AddGamePresenter implements AddGameResponse {

	private AddGameView view;

	public AddGamePresenter(AddGameView view) {
		this.view = view;
	}

	private void displayMessage(String message) {
		view.displayMessage(message);
	}

	@Override
	public void onGameWithNameAlreadyExists(String name) {
		String message = AddGameViewMessages.ADD_GAME_GAME_ALREADY_EXISTS;
		message = message.replace("$game$", name);
		displayMessage(message);
	}

	@Override
	public void onGameSuccessfullyAdded(String name) {
		String message = AddGameViewMessages.ADD_GAME_SUCCESSFULLY_ADDED_GAME;
		message = message.replace("$game$", name);
		displayMessage(message);
	}

	@Override
	public void onInvalidName() {
		displayMessage(AddGameViewMessages.ADD_GAME_INVALID_NAME);
	}

	@Override
	public void onNoPermission() {
		displayMessage(AddGameViewMessages.ADD_GAME_NO_PERMISSION);
	}

}
