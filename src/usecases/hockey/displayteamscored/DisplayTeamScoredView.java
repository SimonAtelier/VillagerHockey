package usecases.hockey.displayteamscored;

import java.util.List;
import java.util.UUID;

public interface DisplayTeamScoredView {
	
	void displayTeamScored(List<UUID> viewers, String team);
	
	void displayTeamScoredTitle(List<UUID> viewers, String team);
	
	void displayTeamScores(List<UUID> viewers, List<ScoreResponseItem> scores);
	
}
