package usecases.displayteamscored;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.MessageView;
import view.impl.ScoreView;
import view.title.TitleView;
import view.title.TitleViewImpl;

public class DisplayTeamScoredViewImpl implements DisplayTeamScoredView {

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayTeamScored(List<UUID> viewers, String team) {
		String message = DisplayTeamScoredMessages.DISPLAY_TEAM_SCORED_GOAL_FOR_TEAM;
		message = message.replace("$team$", team);
		for (UUID viewer : viewers) {
			displayMessage(viewer, message);
		}
	}

	@Override
	public void displayTeamScoredTitle(List<UUID> viewers, String team) {
		String title = DisplayTeamScoredMessages.DISPLAY_TEAM_SCORED_GOAL_TITLE;
		String subtitle = DisplayTeamScoredMessages.DISPLAY_TEAM_SCORED_GOAL_SUBTITLE;
		subtitle = subtitle.replace("$team$", team);
		TitleView titleView = new TitleViewImpl();
		titleView.setTitle(title);
		titleView.setSubtitle(subtitle);
		titleView.setFadeInTimeInSeconds(1);
		titleView.setStayTimeInSeconds(2);
		titleView.setFadeOutTimeInSeconds(1);
		for (UUID viewer : viewers) {
			titleView.setViewer(viewer);
			titleView.display();
		}
	}

	@Override
	public void displayTeamScores(List<UUID> viewers, List<ScoreResponseItem> scores) {
		for (UUID viewer : viewers) {
			ScoreView view = new ScoreView();
			view.update(viewer, scores);
		}
	}
	
}
