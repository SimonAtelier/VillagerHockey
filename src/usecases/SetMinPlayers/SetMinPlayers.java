package usecases.SetMinPlayers;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface SetMinPlayers {

	void execute(UUID player, String game, String minPlayers, SetMinPlayersResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface SetMinPlayersResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onMinPlayersIsNotAValidNumber(String minPlayers);
		
		void onMinPlayersSuccessfullySet(String game, String minPlayers);
		
	}
	
}
