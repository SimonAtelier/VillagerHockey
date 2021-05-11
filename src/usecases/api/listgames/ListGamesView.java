package usecases.api.listgames;

import java.util.List;

public interface ListGamesView {

	void displayNoGamesToList();
	
	void displayNoPermission();
	
	void displayGamesList(List<GameListItem> games);
	
}
