package usecases.pinata;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PlayerGateway;

public interface Pinata {

	void execute(PinataRequest request, PinataResponse response);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	void setGameGateway(GameGateway gameGateway);;
	
	public interface PinataRequest {
		
		String getGameName();
		
	}
	
	public interface PinataResponse {
		
		void onPresent(List<UUID> players);
		
	}
	
}
