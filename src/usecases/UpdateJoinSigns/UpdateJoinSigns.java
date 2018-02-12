package usecases.UpdateJoinSigns;

import gateways.GameGateway;
import gateways.SignGateway;

public interface UpdateJoinSigns {
	
	void execute(UpdateJoinSignsRequest request, UpdateJoinSignsResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setSignGateway(SignGateway signGateway);
	
	public interface UpdateJoinSignsRequest {
		
		int getPlayersCount();
		
		int getMaximumAmountOfPlayers();
		
		String getGame();
		
		String getGameState();
		
	}
	
	public interface UpdateJoinSignsResponse {
		
	}

}
