package usecases.AddTeam;

import java.util.List;

import usecases.AddTeam.AddTeam.AddTeamResponse;

public class AddTeamPresenter implements AddTeamResponse {

	private AddTeamView view;
	
	public AddTeamPresenter(AddTeamView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onNoSuchGame(String name) {
		view.displayNoSuchGame(name);	
	}

	@Override
	public void onTeamWithNameAlreadyExists(String name) {
		view.displayTeamWithNameAlreadyExists(name);
	}
	
	@Override
	public void onTeamWithColorAlreadyExists(String color) {
		view.displayTeamWithColorAlreadyExists(color);
	}

	@Override
	public void onInvalidTeamName(String name) {
		view.displayInvalidTeamName(name);
	}

	@Override
	public void onTeamSuccessfullyAdded(String game, String team) {
		view.displayTeamSuccessfullyAdded(game, team);
	}

	@Override
	public void onTeamColorIsNotValid(String color, List<String> possibleValues) {
		view.displayTeamColorIsNotValid(color, possibleValues);
	}

	@Override
	public void onMaximumAmountOfTeamsAlreadyReached(int maximum) {
		view.displayMaximumAmountOfTeamsAlreadyReached(maximum + "");
	}
	
}
