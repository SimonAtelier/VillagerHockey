package usecases.destroypinata;

import java.util.UUID;

import gamestats.GameStatisticGateway;
import gateways.GameGateway;
import gateways.PlayerGateway;

public interface DestroyPinata {
	
	void execute(DestroyPinataRequest request, DestroyPinataResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	void setGameStatisticGateway(GameStatisticGateway gameStatisticGateway);
	
	public interface DestroyPinataRequest {
	
		UUID getDestroyer();
		
	}
	
	public interface DestroyPinataResponse {
		
		void onDestroyedPinata(DestroyPinataResponseModel responseModel);
		
	}

}
