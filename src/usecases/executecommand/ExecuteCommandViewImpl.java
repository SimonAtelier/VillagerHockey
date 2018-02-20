package usecases.executecommand;

import java.util.UUID;

import context.Context;
import view.MessageView;

public class ExecuteCommandViewImpl implements ExecuteCommandView {

	private UUID viewer;
	
	public ExecuteCommandViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayToManyArguments(String name, String syntax) {
		String message = ExecuteCommandViewMessages.EXECUTE_COMMAND_TOO_MANY_ARGUMENTS;
		message = message.replace("$name$", name);
		message = message.replace("$syntax$", syntax);
		displayMessage(viewer, message);
	}

	@Override
	public void displayMissingArguments(String name, String syntax) {
		String message = ExecuteCommandViewMessages.EXECUTE_COMMAND_MISSING_ARGUMENTS;
		message = message.replace("$name$", name);
		message = message.replace("$syntax$", syntax);
		displayMessage(viewer, message);
	}

	@Override
	public void displayUnknownCommand(String name) {
		String message = ExecuteCommandViewMessages.EXECUTE_COMMAND_UNKNOWN_COMMAND;
		message = message.replace("$name$", name);
		displayMessage(viewer, message);
	}

}
