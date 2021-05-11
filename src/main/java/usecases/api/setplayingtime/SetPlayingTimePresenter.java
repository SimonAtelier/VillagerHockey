package usecases.api.setplayingtime;

import usecases.api.setplayingtime.SetPlayingTime.SetPlayingTimeResponse;

public class SetPlayingTimePresenter implements SetPlayingTimeResponse {

	private SetPlayingTimeView view;
	
	public SetPlayingTimePresenter(SetPlayingTimeView view) {
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
	public void onPlayingTimeIsNotAValidNumber(String playingTime) {
		view.displayPlayingTimeIsNotAValidNumber(playingTime);
	}

	@Override
	public void onPlayingTimeSuccessfullySet(String game, String playingTime) {
		view.displayPlayingTimeSuccessfullySet(game, playingTime);
	}

}
