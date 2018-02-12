package usecases.ShowTeams;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;

public interface ShowTeams {

	void execute(ShowTeamsRequest request, ShowTeamsResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface ShowTeamsRequest {
		
		UUID getPlayer();
		
	}
	
	public interface ShowTeamsResponse {
		
		void onPlayerIsNotIngame();
		
		void presentTeams(List<ShowTeamsResponseItem> teams);
				
	}
	
}
