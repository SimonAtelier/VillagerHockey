package usecases.DisplayTeamScored;

import java.util.List;
import java.util.UUID;

import usecases.DisplayTeamScored.DisplayTeamScored.DisplayTeamScoredResponse;

public class DisplayTeamScoredPresenter implements DisplayTeamScoredResponse {

	private DisplayTeamScoredView view;
	
	public DisplayTeamScoredPresenter(DisplayTeamScoredView view) {
		this.view = view;
	}

	@Override
	public void presentTeamScored(List<UUID> viewers, String team) {
		view.displayTeamScored(viewers, team);
		view.displayTeamScoredTitle(viewers, team);
	}

	@Override
	public void presentScore(List<UUID> viewers, List<ScoreResponseItem> scores) {
		view.displayTeamScores(viewers, scores);
	}
	
}
