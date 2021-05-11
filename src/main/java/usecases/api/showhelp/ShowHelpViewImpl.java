package usecases.api.showhelp;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class ShowHelpViewImpl implements ShowHelpView {
	
	private UUID viewer;
	
	public ShowHelpViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, ShowHelpViewMessages.SHOW_HELP_NO_PERMISSION);
	}

	@Override
	public void displayHelp(String content) {
		displayMessage(viewer, content);
	}

}
