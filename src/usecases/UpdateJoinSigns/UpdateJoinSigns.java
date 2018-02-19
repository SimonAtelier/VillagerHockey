package usecases.UpdateJoinSigns;

import gateways.GameGateway;
import gateways.JoinSignGateway;
import gateways.SignGateway;

public interface UpdateJoinSigns {
	
	void execute(UpdateJoinSignsRequest request, UpdateJoinSignsResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setSignGateway(SignGateway signGateway);
	
	void setJoinSignGateway(JoinSignGateway joinSignGateway);
	
	public interface UpdateJoinSignsRequest {
		
		int getPlayersCount();
		
		int getMaximumAmountOfPlayers();
		
		String getGame();
		
		String getGameState();
		
	}
	
	public interface UpdateJoinSignsResponse {
		
	}

}
