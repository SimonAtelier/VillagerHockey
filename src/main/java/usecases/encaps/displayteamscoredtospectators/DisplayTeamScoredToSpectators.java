package usecases.encaps.displayteamscoredtospectators;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;
import gateways.PlayerGateway;
import usecases.hockey.displayteamscored.ScoreResponseItem;

public interface DisplayTeamScoredToSpectators {

	void execute(String game, String team, DisplayTeamScoredToSpectatorsResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface DisplayTeamScoredToSpectatorsResponse {
		
		void presentTeamScored(List<UUID> viewers, String team);
		
		void presentScore(List<UUID> viewers, List<ScoreResponseItem> scores);
		
	}
	
}
