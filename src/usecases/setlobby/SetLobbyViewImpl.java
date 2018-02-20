package usecases.setlobby;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class SetLobbyViewImpl implements SetLobbyView {

	private UUID viewer;

	public SetLobbyViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, SetLobbyViewMessages.SET_LOBBY_NO_PERMISSION);
	}

	@Override
	public void displayNoGameWithSuchName(String game) {
		String message = SetLobbyViewMessages.SET_LOBBY_NO_GAME_WITH_SUCH_NAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displayLobbySuccessfullySet(String game) {
		String message = SetLobbyViewMessages.SET_LOBBY_SUCCESSFULLY_SET;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

}
