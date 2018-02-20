package usecases.displayteamscored;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.impl.ScoreView;
import view.message.MessageView;
import view.title.TitleView;
import view.title.TitleViewImpl;
import view.title.TitleViewModel;

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
		TitleViewModel model = titleView.getTitleViewModel();
		model.setTitle(title);
		model.setSubtitle(subtitle);
		model.setTitleFadeInTimeInSeconds(1);
		model.setTitleFadeOutTimeInSeconds(1);
		model.setTitleStayTimeInSeconds(2);
		model.setSubtitleFadeInTimeInSeconds(1);
		model.setSubtitleFadeOutTimeInSeconds(1);
		model.setSubtitleStayTimeInSeconds(2);
		
		for (UUID viewer : viewers) {
			titleView.display(viewer);
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
