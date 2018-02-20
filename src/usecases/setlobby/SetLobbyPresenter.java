package usecases.setlobby;

import usecases.setlobby.SetLobby.SetLobbyResponse;

public class SetLobbyPresenter implements SetLobbyResponse {

	private SetLobbyView view;
	
	public SetLobbyPresenter(SetLobbyView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}
	
	@Override
	public void onNoGameWithSuchName(String game) {
		view.displayNoGameWithSuchName(game);
	}

	@Override
	public void onLobbySuccessfullySet(String game) {
		view.displayLobbySuccessfullySet(game);
	}
	
}
