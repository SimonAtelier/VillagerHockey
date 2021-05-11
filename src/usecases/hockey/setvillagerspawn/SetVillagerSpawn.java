package usecases.hockey.setvillagerspawn;

import java.util.UUID;

public interface SetVillagerSpawn {

	void execute(SetVillagerSpawnRequest request, SetVillagerSpawnResponse response);
	
	public interface SetVillagerSpawnRequest {
		
		double getX();
		
		double getY();
		
		double getZ();
		
		double getPitch();
		
		double getYaw();
		
		String getWorld();
		
		String getGame();
		
		UUID getPlayer();
		
	}
	
	public interface SetVillagerSpawnResponse {
		
		void onNoPermission();
		
		void onNoSuchGame();
		
		void onVillagerSpawnSuccessfullySet(String game);
		
	}
	
}
