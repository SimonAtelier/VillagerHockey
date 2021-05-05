package usecases.addteam;

import java.util.List;

import usecases.addteam.AddTeam.AddTeamResponse;

public class AddTeamPresenter implements AddTeamResponse {

	private AddTeamView view;
	
	public AddTeamPresenter(AddTeamView view) {
		this.view = view;
	}
	
	private void displayMessage(String message) {
		view.displayMessage(message);
	}
	
	@Override
	public void onNoSuchGame(String name) {
		String message = AddTeamViewMessages.ADD_TEAM_NO_SUCH_GAME;
		message = message.replace("$game$", name);
		displayMessage(message);
	}

	@Override
	public void onNoPermission() {
		displayMessage(AddTeamViewMessages.ADD_TEAM_NO_PERMISSION);
	}

	@Override
	public void onTeamWithNameAlreadyExists(String name) {
		String message = AddTeamViewMessages.ADD_TEAM_NAME_ALREADY_EXISTS;
		message = message.replace("$team$", name);
		displayMessage(message);
	}
	
	@Override
	public void onTeamWithColorAlreadyExists(String color) {
		String message = AddTeamViewMessages.ADD_TEAM_COLOR_ALREADY_EXITS;
		message = message.replace("$color$", color);
		displayMessage(message);
	}

	@Override
	public void onInvalidTeamName(String name) {
		String message = AddTeamViewMessages.ADD_TEAM_NAME_IS_INVALID;
		message = message.replace("$team$", name);
		displayMessage(message);
	}

	@Override
	public void onTeamSuccessfullyAdded(String game, String team) {
		String message = AddTeamViewMessages.ADD_TEAM_SUCCESS;
		message = message.replace("$game$", game);
		message = message.replace("$team$", team);
		displayMessage(message);
	}

	@Override
	public void onTeamColorIsNotValid(String color, List<String> possibleValues) {
		String message = AddTeamViewMessages.ADD_TEAM_COLOR_IS_NOT_VALID;
		message = message.replace("$color$", color);
		message = message.replace("$values$", createPossibleTeamColorValuesView(possibleValues));
		displayMessage(message);
	}
	
	@Override
	public void onMaximumAmountOfTeamsAlreadyReached(int maximum) {
		String message = AddTeamViewMessages.ADD_TEAM_MAXIMUM_AMOUNT_ALREADY_REACHED;
		message = message.replace("$maximum$", maximum + "");
		displayMessage(message);
	}

	private String createPossibleTeamColorValuesView(List<String> values) {
		StringBuffer buffer = new StringBuffer();
		for (String value : values) {
			buffer.append(" {");
			buffer.append(value);
			buffer.append("}");
		}
		return buffer.toString();
	}
	
}
