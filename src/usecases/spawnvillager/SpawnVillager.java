package usecases.spawnvillager;

import java.util.List;
import java.util.UUID;

import entities.config.Configuration;
import gateways.GameGateway;

public interface SpawnVillager {
	
	void execute(SpawnVillagerRequest request, SpawnVillagerResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setConfiguration(Configuration configuration);
	
	public interface SpawnVillagerRequest {
		
		String getGame();
		
	}
	
	public interface SpawnVillagerResponse {
		
		void onSpecialRound(List<UUID> players);
		
	}

}
