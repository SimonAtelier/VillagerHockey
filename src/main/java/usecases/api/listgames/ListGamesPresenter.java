package usecases.api.listgames;

import java.util.List;

import usecases.api.listgames.ListGames.ListGamesResponse;

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
	public void present(List<GameListItem> games) {
		view.displayGamesList(games);
	}	
	
}
