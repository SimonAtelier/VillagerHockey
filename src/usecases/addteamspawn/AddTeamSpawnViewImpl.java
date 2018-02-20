package usecases.addteamspawn;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class AddTeamSpawnViewImpl implements AddTeamSpawnView {
	
	private UUID viewer;

	public AddTeamSpawnViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoSuchGame() {
		displayMessage(viewer, AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_NO_SUCH_GAME);
	}

	@Override
	public void displayNoSuchTeam() {
		displayMessage(viewer, AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_NO_SUCH_TEAM);
	}

	@Override
	public void displayTeamSpawnSuccessfullyAdd(String team) {
		String message = AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_SUCCESSFULLY_ADD;
		message = message.replace("$team$", team);
		displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_NO_PERMISSION);
	}

}
