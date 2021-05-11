package usecases.hockey.spawnvillager;

import java.util.List;
import java.util.UUID;

import entities.config.Configuration;
import gateways.GameGateway;

public interface SpawnVillager {
	
	void execute(SpawnVillagerRequest request, SpawnVillagerResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setConfiguration(Configuration configuration);
	
	public interface SpawnVillagerRequest {
		
		boolean isPassenger();
		
		String getGame();
		
		String getPassengerName();
		
	}
	
	public interface SpawnVillagerResponse {
		
		void onPresent(List<UUID> players);
		
	}

}
