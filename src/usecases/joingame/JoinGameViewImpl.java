package usecases.joingame;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.messageview.MessageView;

public class JoinGameViewImpl implements JoinGameView {

	private UUID viewer;

	public JoinGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayNoSuchGame(String name) {
		String message = JoinGameViewMessages.JOIN_GAME_NO_SUCH_GAME;
		message = message.replace("$name$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayPlayerAlreadyJoinedAGame() {
		displayMessage(viewer, JoinGameViewMessages.JOIN_GAME_PLAYER_ALREADY_JOINED_A_GAME);
	}
	
	@Override
	public void displayCannotJoin() {
		displayMessage(viewer, JoinGameViewMessages.JOIN_GAME_PLAYER_CANNOT_JOIN);
	}
	
	@Override
	public void displayMaximumAmountOfPlayersReached() {
		displayMessage(viewer, JoinGameViewMessages.JOIN_GAME_MAXIMUM_AMOUNT_OF_PLAYERS_REACHED);
	}

	@Override
	public void displayNoPermission() {
		displayMessage(viewer, JoinGameViewMessages.JOIN_GAME_NO_PERMISSION);
	}

	@Override
	public void displayPlayerJoin(List<UUID> viewers, String player) {
		String message = JoinGameViewMessages.JOIN_GAME_PLAYER_JOIN;
		message = message.replace("$player$", player);
		for (UUID viewer : viewers) {
			displayMessage(viewer, message);
		}
	}

}
