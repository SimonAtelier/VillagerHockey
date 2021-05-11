package usecases.hockey.boatboogie;

import gateways.GameGateway;
import gateways.PlayerGateway;

public interface BoatBoogie {
	
	void execute(BoatBoogieRequest request, BoatBoogieResponse response);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface BoatBoogieRequest {
		
		String getGameName();
		
	}
	
	public interface BoatBoogieResponse {
	
		void onPresentBoats(BoatBoogieResponseModel responseModel);
		
	}

}
