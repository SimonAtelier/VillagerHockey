package usecases.setminplayers;

import usecases.setminplayers.SetMinPlayers.SetMinPlayersResponse;

public class SetMinPlayersPresenter implements SetMinPlayersResponse {

	private SetMinPlayersView view;
	
	public SetMinPlayersPresenter(SetMinPlayersView view) {
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
	public void onMinPlayersIsNotAValidNumber(String minPlayers) {
		view.displayMinPlayersIsNotAValidNumber(minPlayers);
	}

	@Override
	public void onMinPlayersSuccessfullySet(String game, String minPlayers) {
		view.displayMinPlayersSuccessfullySet(game, minPlayers);
	}

}
