package usecases.JoinGame;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;

public interface JoinGame {

	void execute(JoinGameRequest request, JoinGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface JoinGameRequest {
		
		UUID getUniquePlayerId();
		
		String getGame();
		
	}
	
	public interface JoinGameResponse {
		
		void onNoSuchGame(String name);
		
		void onPlayerAlreadyJoinedAGame();
		
		void onPlayerCannotJoin();
		
		void onMaximumAmountOfPlayersReached();
		
		void onNoPermission();
		
		void presentPlayerJoin(List<UUID> players, String player);
		
	}
	
}
