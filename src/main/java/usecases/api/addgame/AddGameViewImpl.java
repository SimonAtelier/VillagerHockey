package usecases.api.addgame;

import java.util.UUID;

import context.Context;
import minigame.view.MessageView;

public class AddGameViewImpl implements AddGameView {

	private UUID viewer;

	public AddGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	@Override
	public void displayMessage(String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
}
