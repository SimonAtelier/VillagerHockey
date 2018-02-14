package usecases.ListGames;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface ListGames {

	void execute(UUID player, ListGamesResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface ListGamesResponse {
		
		void onNoGamesToList();
		
		void onNoPermission();
		
		void present(List<GameListItem> games);
		
	}
	
}
