package usecases.encaps.displaywinner;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;

public interface DisplayWinner {

	void execute(DisplayWinnerRequest request, DisplayWinnerResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface DisplayWinnerRequest {
		
		String getGame();
		
	}
	
	public interface DisplayWinnerResponse {
		
		void presentWinner(List<UUID> viewers, String team);
		
		void presentDraw(List<UUID> viewers);
		
	}
	
}
