package usecases.setgoal;

import java.util.UUID;

import context.Context;
import view.MessageView;

public class SetGoalViewImpl implements SetGoalView {

	private UUID viewer;

	public SetGoalViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoSuchGame(String name) {
		String message = SetGoalViewMessages.SET_GOAL_NO_SUCH_GAME;
		message = message.replace("$name$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayNoSuchTeam(String name) {
		String message = SetGoalViewMessages.SET_GOAL_NO_SUCH_TEAM;
		message = message.replace("$name$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayInvalidLocationId(String id) {
		String message = SetGoalViewMessages.SET_GOAL_INVALID_LOCATION_ID;
		message = message.replace("$id$", id);
		displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, SetGoalViewMessages.SET_GOAL_NO_PERMISSION);
	}

	@Override
	public void displayGoalLocationSuccessfullySet(String locationId) {
		String message = SetGoalViewMessages.SET_GOAL_LOCATION_SUCCESSFULLY_SET;
		message = message.replace("$id$", locationId);
		displayMessage(viewer, message);
	}

	@Override
	public void displayNeedToSetLocationWithIdFirst(String id) {
		String message = SetGoalViewMessages.SET_GOAL_NEED_TO_SET_LOCATION_FIRST;
		message = message.replace("$id$", id);
		displayMessage(viewer, message);
	}

}
