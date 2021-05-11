package usecases.hockey.displayteamscored;

import java.util.List;
import java.util.UUID;

import entities.TeamColor;
import gateways.GameGateway;

public interface DisplayTeamScored {

	void execute(DisplayTeamScoredRequest request, DisplayTeamScoredResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface DisplayTeamScoredRequest {
	
		String getGame();
		
		String getTeam();
		
	}
	
	public interface DisplayTeamScoredResponse {
		
		void presentTeamScored(List<UUID> viewers, String team, TeamColor color);
		
		void presentScore(List<UUID> viewers, List<ScoreResponseItem> scores);
		
	}
		
}
