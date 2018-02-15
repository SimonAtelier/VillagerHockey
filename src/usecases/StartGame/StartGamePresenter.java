package usecases.StartGame;

import usecases.StartGame.StartGame.StartGameResponse;

public class StartGamePresenter implements StartGameResponse {

	private StartGameView view;
	
	public StartGamePresenter(StartGameView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onNoSuchGame(String game) {
		view.displayNoSuchGame(game);
	}

	@Override
	public void onAlreadyStarted(String game) {
		view.displayAlreadyStarted(game);
	}

	@Override
	public void onSuccessfullyStarted(String game) {
		view.displaySuccessfullyStarted(game);
	}

}
