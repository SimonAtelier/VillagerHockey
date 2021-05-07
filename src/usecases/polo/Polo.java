package usecases.polo;

import gateways.GameGateway;
import gateways.PlayerGateway;

public interface Polo {

	void execute(PoloRequest request, PoloResponse response);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface PoloRequest {
		
		String getGameName();
		
	}
	
	public interface PoloResponse {
	
		void onPresent(PoloResponseModel responseModel);
		
	}
	
}
