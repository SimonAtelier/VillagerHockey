package game.UseCases.PreparePlayerForGame;

import java.util.UUID;

public interface PreparePlayerForGameView {
	
	void displayHockeySticks(UUID uniquePlayerId);
	
	void displayScores(UUID uniquePlayerId);
	
}
