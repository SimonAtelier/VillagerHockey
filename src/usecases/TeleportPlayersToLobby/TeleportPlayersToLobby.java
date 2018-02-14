package usecases.TeleportPlayersToLobby;

import java.util.List;
import java.util.UUID;

import entities.Location;
import gateways.GameGateway;

public interface TeleportPlayersToLobby {

	void execute(String game, TeleportPlayersToLobbyResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface TeleportPlayersToLobbyResponse {
		
		void presentLocation(List<UUID> players, Location location);
		
	}
	
}
