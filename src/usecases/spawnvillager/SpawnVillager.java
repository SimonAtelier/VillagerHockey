package usecases.spawnvillager;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;

public interface SpawnVillager {
	
	void execute(SpawnVillagerRequest request, SpawnVillagerResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface SpawnVillagerRequest {
		
		String getGame();
		
	}
	
	public interface SpawnVillagerResponse {
		
		void onSpecialRound(List<UUID> players);
		
	}

}
