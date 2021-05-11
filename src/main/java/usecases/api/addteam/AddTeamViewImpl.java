package usecases.api.addteam;

import java.util.UUID;

import context.Context;
import minigame.view.MessageView;

public class AddTeamViewImpl implements AddTeamView {
	
	private UUID viewer;
	
	public AddTeamViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	public void displayMessage(String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

}
