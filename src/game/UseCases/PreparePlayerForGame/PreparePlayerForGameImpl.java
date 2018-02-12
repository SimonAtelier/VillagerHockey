package game.UseCases.PreparePlayerForGame;

import java.util.UUID;

public class PreparePlayerForGameImpl implements PreparePlayerForGame {

	@Override
	public void execute(UUID uniquePlayerId, PreparePlayerForGameResponse response) {
		response.presentHockeySticks(uniquePlayerId);
		response.presentScores(uniquePlayerId);
	}
	
}
