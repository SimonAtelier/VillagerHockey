package usecases.stopgame;

import java.util.List;
import java.util.UUID;

import usecases.stopgame.StopGame.StopGameResponse;

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

	@Override
	public void presentStopping(List<UUID> viewers, String game) {
		view.displayStopping(viewers, game);
	}

}
