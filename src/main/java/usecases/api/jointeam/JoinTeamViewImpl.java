package usecases.api.jointeam;

import java.util.List;
import java.util.UUID;

import context.Context;
import minigame.view.MessageView;

public class JoinTeamViewImpl implements JoinTeamView {

	private UUID viewer;
	
	public JoinTeamViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, JoinTeamViewMessages.JOIN_TEAM_NO_PERMISSION);
	}

	@Override
	public void displayNowInTeam(UUID player, String team) {
		String message = JoinTeamViewMessages.JOIN_TEAM_NOW_IN_TEAM;
		message = message.replace("$team$", team);
		displayMessage(player, message);
	}

	@Override
	public void displayJoin(List<UUID> players, String player, String team) {
		String message = JoinTeamViewMessages.JOIN_TEAM_PLAYER_JOIN;
		message = message.replace("$player$", player);
		message = message.replace("$team$", team);
		for (UUID viewer : players) {
			displayMessage(viewer, message);
		}
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = JoinTeamViewMessages.JOIN_TEAM_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displayNoSuchTeam(String team) {
		String message = JoinTeamViewMessages.JOIN_TEAM_NO_SUCH_TEAM;
		message = message.replace("$team$", team);
		displayMessage(viewer, message);
	}

	@Override
	public void displayTeamIsAlreadyFull(String team) {
		String message = JoinTeamViewMessages.JOIN_TEAM_TEAM_ALREADY_FULL;
		message = message.replace("$team$", team);
		displayMessage(viewer, message);
	}

	@Override
	public void displayAlreadyJoinedATeam() {
		displayMessage(viewer, JoinTeamViewMessages.JOIN_TEAM_ALREADY_JOINED_A_TEAM);	
	}

}
