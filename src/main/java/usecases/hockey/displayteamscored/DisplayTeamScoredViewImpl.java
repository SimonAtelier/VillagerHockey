package usecases.hockey.displayteamscored;

import java.util.List;
import java.util.UUID;

import context.Context;
import minigame.view.MessageView;
import minigame.view.TitleView;
import minigame.view.TitleViewModel;
import view.score.HockeyScoreView;

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

		TitleView titleView = Context.viewFactory.createTitleView();
		TitleViewModel model = titleView.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
		model.setFadeInTimeInSeconds(1);
		model.setFadeOutTimeInSeconds(1);
		model.setStayTimeInSeconds(2);
		
		for (UUID viewer : viewers) {
			titleView.display(viewer);
		}
	}

	@Override
	public void displayTeamScores(List<UUID> viewers, List<ScoreResponseItem> scores) {
		for (UUID viewer : viewers) {
			HockeyScoreView view = new HockeyScoreView();
			view.update(viewer, scores);
		}
	}
	
}
