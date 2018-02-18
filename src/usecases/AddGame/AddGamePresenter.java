package usecases.AddGame;

import usecases.AddGame.AddGame.AddGameResponse;

public class AddGamePresenter implements AddGameResponse {

	private AddGameView view;
	
	public AddGamePresenter(AddGameView view) {
		this.view = view;
	}

	@Override
	public void onGameWithNameAlreadyExists(String name) {
		view.displayGameWithNameAlreadyExists(name);
	}

	@Override
	public void onGameSuccessfullyAdded(String name) {
		view.displayGameSuccessfullyAdded(name);
	}
	
	@Override
	public void onInvalidName() {
		view.displayInvalidName();
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

}
