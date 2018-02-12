package usecases.SetLobby;

import java.util.UUID;

public interface SetLobby {

	void execute(SetLobbyRequest request, SetLobbyResponse response);
	
	public interface SetLobbyRequest {

		double getX();
		
		double getY();
		
		double getZ();
		
		double getPitch();
		
		double getYaw();
		
		String getWorld();
		
		String getGame();
		
		UUID getPlayer();
		
	}
	
	public interface SetLobbyResponse {
		
		void onNoGameWithSuchName(String game);
		
		void onNoPermission();
		
		void onLobbySuccessfullySet(String game);
		
	}
	
}
