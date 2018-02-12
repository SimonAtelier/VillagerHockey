package usecases.LeaveGame;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PlayerGateway;

public interface LeaveGame {

	void execute(UUID uniquePlayerId, LeaveGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface LeaveGameResponse {
		
		void onPlayerIsNotIngame();
		
		void presentPlayerLeave(List<UUID> players, String player);
		
	}
	
}
