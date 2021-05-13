package usecases.encaps.prepareplayerforgame;

import java.util.UUID;

import gateways.GameGateway;

public interface PreparePlayerForGame {

	void execute(PreparePlayerForGameRequest request, PreparePlayerForGameResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface PreparePlayerForGameRequest {
		
		String getGameName();
		
		UUID getUniquePlayerId();
		
	}
	
	public interface PreparePlayerForGameResponse {
		
		void presentHockeySticks(UUID uniquePlayerId);
		
		void presentScores(UUID uniquePlayerId);
		
	}
	
}
