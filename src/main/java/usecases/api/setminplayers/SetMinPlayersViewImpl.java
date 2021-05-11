package usecases.api.setminplayers;

import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class SetMinPlayersViewImpl implements SetMinPlayersView {

	private UUID viewer;
	
	public SetMinPlayersViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, SetMinPlayersViewMessages.SET_MIN_PLAYERS_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = SetMinPlayersViewMessages.SET_MIN_PLAYERS_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displayMinPlayersIsNotAValidNumber(String minPlayers) {
		String message = SetMinPlayersViewMessages.SET_MIN_PLAYERS_NOT_A_NUMBER;
		message = message.replace("$number$", minPlayers);
		displayMessage(viewer, message);
	}

	@Override
	public void displayMinPlayersSuccessfullySet(String game, String minPlayers) {
		String message = SetMinPlayersViewMessages.SET_MIN_PLAYERS_SUCCESS;
		message = message.replace("$game$", game);
		message = message.replace("$number$", minPlayers);
		displayMessage(viewer, message);
	}
	
}
