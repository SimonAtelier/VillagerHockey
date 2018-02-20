package usecases.addteamspawn;

import usecases.addteamspawn.AddTeamSpawn.AddTeamSpawnResponse;

public class AddTeamSpawnPresenter implements AddTeamSpawnResponse {

	private AddTeamSpawnView view;
	
	public AddTeamSpawnPresenter(AddTeamSpawnView view) {
		this.view = view;
	}
	
	@Override
	public void onNoSuchGame() {
		view.displayNoSuchGame();
	}

	@Override
	public void onNoSuchTeam() {
		view.displayNoSuchTeam();
	}

	@Override
	public void onTeamSpawnSuccessfullyAdd(String team) {
		view.displayTeamSpawnSuccessfullyAdd(team);
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}
	
}
