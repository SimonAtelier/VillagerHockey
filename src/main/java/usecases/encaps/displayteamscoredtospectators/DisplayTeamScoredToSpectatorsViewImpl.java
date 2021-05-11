package usecases.encaps.displayteamscoredtospectators;

import java.util.List;
import java.util.UUID;

import context.Context;
import usecases.hockey.displayteamscored.ScoreResponseItem;
import view.message.MessageView;

public class DisplayTeamScoredToSpectatorsViewImpl implements DisplayTeamScoredToSpectatorsView {

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	private String createScoreMessage(List<ScoreResponseItem> scores) {
		StringBuffer stringBuffer = new StringBuffer();
		
		for (ScoreResponseItem score : scores) {
			stringBuffer.append(score.getTeam());
			stringBuffer.append(": ");
			stringBuffer.append(score.getScore());
			stringBuffer.append(" ");
		}
	
		return stringBuffer.toString();
	}
	
	@Override
	public void displayTeamScored(List<UUID> viewers, String team) {
		String message = DisplayTeamScoredToSpectatorsViewMessages.DISPLAY_TEAM_SCORED_TO_SPECTATORS_GOAL_FOR_TEAM;
		message = message.replace("$team$", team);
		
		for (UUID viewer : viewers)
			displayMessage(viewer, message);
	}

	@Override
	public void displayTeamScores(List<UUID> viewers, List<ScoreResponseItem> scores) {
		String message = createScoreMessage(scores);
		for (UUID viewer : viewers)
			displayMessage(viewer, message);
	}

}
