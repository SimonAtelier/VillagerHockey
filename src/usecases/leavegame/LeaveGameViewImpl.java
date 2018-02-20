package usecases.leavegame;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.MessageView;

public class LeaveGameViewImpl implements LeaveGameView {

	private UUID viewer;

	public LeaveGameViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}

	@Override
	public void displayPlayerIsNotIngame() {
		displayMessage(viewer, LeaveGameViewMessages.LEAVE_GAME_PLAYER_NOT_INGAME);
	}

	@Override
	public void displayPlayerLeave(List<UUID> viewers, String player) {
		String message = LeaveGameViewMessages.LEAVE_GAME_PLAYER_LEAVE_GAME;
		message = message.replace("$player$",player);
		for (UUID viewer : viewers) {
			displayMessage(viewer, message);
		}
	}

}
