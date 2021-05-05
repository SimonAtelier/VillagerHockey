package usecases.addteamspawn;

import usecases.addteamspawn.AddTeamSpawn.AddTeamSpawnResponse;

public class AddTeamSpawnPresenter implements AddTeamSpawnResponse {

	private AddTeamSpawnView view;
	
	public AddTeamSpawnPresenter(AddTeamSpawnView view) {
		this.view = view;
	}
	
	private void displayMessage(String message) {
		view.displayMessage(message);
	}
	
	@Override
	public void onNoSuchGame() {
		displayMessage(AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_NO_SUCH_GAME);
	}

	@Override
	public void onNoSuchTeam() {
		displayMessage(AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_NO_SUCH_TEAM);
	}

	@Override
	public void onTeamSpawnSuccessfullyAdd(String team) {
		String message = AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_SUCCESSFULLY_ADD;
		message = message.replace("$team$", team);
		displayMessage(message);
	}

	@Override
	public void onNoPermission() {
		displayMessage(AddTeamSpawnViewMessages.ADD_TEAM_SPAWN_NO_PERMISSION);
	}
	
}
