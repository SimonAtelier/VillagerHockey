package usecases.hockey.displayteamscored;

import java.util.List;
import java.util.UUID;

import entities.ColorUtil;
import entities.TeamColor;
import minigame.view.MessageCodes;
import usecases.hockey.displayteamscored.DisplayTeamScored.DisplayTeamScoredResponse;

public class DisplayTeamScoredPresenter implements DisplayTeamScoredResponse {

	private DisplayTeamScoredView view;
	
	public DisplayTeamScoredPresenter(DisplayTeamScoredView view) {
		this.view = view;
	}

	@Override
	public void presentTeamScored(List<UUID> viewers, String team, TeamColor color) {
		view.displayTeamScored(viewers, ColorUtil.toMessageCode(color) + team + MessageCodes.RESET);
		view.displayTeamScoredTitle(viewers, ColorUtil.toMessageCode(color) + team + MessageCodes.RESET);
	}

	@Override
	public void presentScore(List<UUID> viewers, List<ScoreResponseItem> scores) {
		view.displayTeamScores(viewers, scores);
	}
	
}
