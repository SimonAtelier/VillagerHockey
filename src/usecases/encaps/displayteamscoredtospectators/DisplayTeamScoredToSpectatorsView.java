package usecases.encaps.displayteamscoredtospectators;

import java.util.List;
import java.util.UUID;

import usecases.hockey.displayteamscored.ScoreResponseItem;

public interface DisplayTeamScoredToSpectatorsView {
		
	void displayTeamScored(List<UUID> viewers, String team);
		
	void displayTeamScores(List<UUID> viewers, List<ScoreResponseItem> scores);

}
