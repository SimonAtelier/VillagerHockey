package usecases.ListGames;

import java.util.List;

import usecases.ListGames.ListGames.ListGamesResponse;

public class ListGamesPresenter implements ListGamesResponse {

	private ListGamesView view;

	public ListGamesPresenter(ListGamesView view) {
		this.view = view;
	}

	@Override
	public void onNoGamesToList() {
		view.displayNoGamesToList();
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void present(List<String> games) {
		view.displayGamesList(games);
	}	
	
}
