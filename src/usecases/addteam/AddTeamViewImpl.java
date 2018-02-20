package usecases.addteam;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.messageview.MessageView;

public class AddTeamViewImpl implements AddTeamView {
	
	private UUID viewer;
	
	public AddTeamViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoSuchGame(String name) {
		String message = AddTeamViewMessages.ADD_TEAM_NO_SUCH_GAME;
		message = message.replace("$game$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, AddTeamViewMessages.ADD_TEAM_NO_PERMISSION);
	}

	@Override
	public void displayTeamWithNameAlreadyExists(String name) {
		String message = AddTeamViewMessages.ADD_TEAM_NAME_ALREADY_EXISTS;
		message = message.replace("$team$", name);
		displayMessage(viewer, message);
	}
	
	@Override
	public void displayTeamWithColorAlreadyExists(String color) {
		String message = AddTeamViewMessages.ADD_TEAM_COLOR_ALREADY_EXITS;
		message = message.replace("$color$", color);
		displayMessage(viewer, message);
	}

	@Override
	public void displayInvalidTeamName(String name) {
		String message = AddTeamViewMessages.ADD_TEAM_NAME_IS_INVALID;
		message = message.replace("$team$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayTeamSuccessfullyAdded(String game, String team) {
		String message = AddTeamViewMessages.ADD_TEAM_SUCCESS;
		message = message.replace("$game$", game);
		message = message.replace("$team$", team);
		displayMessage(viewer, message);
	}

	@Override
	public void displayTeamColorIsNotValid(String color, List<String> possibleValues) {
		String message = AddTeamViewMessages.ADD_TEAM_COLOR_IS_NOT_VALID;
		message = message.replace("$color$", color);
		message = message.replace("$values$", createPossibleTeamColorValuesView(possibleValues));
		displayMessage(viewer, message);
	}
	
	@Override
	public void displayMaximumAmountOfTeamsAlreadyReached(String maximum) {
		String message = AddTeamViewMessages.ADD_TEAM_MAXIMUM_AMOUNT_ALREADY_REACHED;
		message = message.replace("$maximum$", maximum);
		displayMessage(viewer, message);
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
