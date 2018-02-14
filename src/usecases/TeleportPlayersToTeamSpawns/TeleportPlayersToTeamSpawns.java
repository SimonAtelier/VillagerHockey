package usecases.TeleportPlayersToTeamSpawns;

import java.util.List;
import java.util.UUID;

import entities.Location;
import gateways.GameGateway;

public interface TeleportPlayersToTeamSpawns {

	void execute(String game, TeleportPlayersToTeamSpawnsResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface TeleportPlayersToTeamSpawnsResponse {
		
		void presentLocation(List<UUID> players, List<Location> locations);
		
	}
	
}
