package usecases.addteamspawn;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class AddTeamSpawnViewImpl implements AddTeamSpawnView {
	
	private UUID viewer;

	public AddTeamSpawnViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	@Override
	public void displayMessage(String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

}
