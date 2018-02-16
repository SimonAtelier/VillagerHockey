package usecases.StopGame;

import usecases.StopGame.StopGame.StopGameResponse;

public class StopGamePresenter implements StopGameResponse {

	private StopGameView view;
	
	public StopGamePresenter(StopGameView view) {
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
	public void onAlreadyStopped(String game) {
		view.displayAlreadyStopped(game);
	}

	@Override
	public void onSuccessfullyStopped(String game) {
		view.displaySuccessfullyStopped(game);
	}

}
