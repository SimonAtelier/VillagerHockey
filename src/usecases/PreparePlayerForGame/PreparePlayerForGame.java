package usecases.PreparePlayerForGame;

import java.util.UUID;

public interface PreparePlayerForGame {

	void execute(UUID uniquePlayerId, PreparePlayerForGameResponse response);
	
	public interface PreparePlayerForGameResponse {
		
		void presentHockeySticks(UUID uniquePlayerId);
		
		void presentScores(UUID uniquePlayerId);
		
	}
	
}
