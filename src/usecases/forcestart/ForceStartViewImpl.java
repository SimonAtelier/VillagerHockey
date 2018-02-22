package usecases.forcestart;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class ForceStartViewImpl implements ForceStartView {

	private UUID viewer;
	
	public ForceStartViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, ForceStartViewMessages.FORCE_START_NO_PERMISSION);
	}

	@Override
	public void displayForcedStart(String game) {
		String message = ForceStartViewMessages.FORCE_START_FORCED_START;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

}
