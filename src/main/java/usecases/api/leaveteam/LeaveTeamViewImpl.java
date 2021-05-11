package usecases.api.leaveteam;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class LeaveTeamViewImpl implements LeaveTeamView {

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayLeftTeam(UUID viewer, String team) {
		String message = LeaveTeamViewMessages.LEAVE_TEAM_NOW_NOT_IN_TEAM;
		message = message.replace("$team$", team);
		displayMessage(viewer, message);
	}

	@Override
	public void displayLeftTeam(List<UUID> viewers, String player, String team) {
		String message = LeaveTeamViewMessages.LEAVE_TEAM_PLAYER_LEFT;
		message = message.replace("$player$", player);
		message = message.replace("$team$", team);
		for (UUID viewer : viewers) {
			displayMessage(viewer, message);
		}
	}

}
