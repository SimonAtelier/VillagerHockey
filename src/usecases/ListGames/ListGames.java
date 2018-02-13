package usecases.ListGames;

import java.util.List;
import java.util.UUID;

public interface ListGames {

	void execute(UUID player, ListGamesResponse response);
	
	public interface ListGamesResponse {
		
		void onNoGamesToList();
		
		void onNoPermission();
		
		void present(List<GameListItem> games);
		
	}
	
}
