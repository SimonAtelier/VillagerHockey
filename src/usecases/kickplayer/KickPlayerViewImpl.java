package usecases.kickplayer;

import java.util.List;
import java.util.UUID;

import context.Context;
import view.message.MessageView;

public class KickPlayerViewImpl implements KickPlayerView {

	private UUID viewer;
	
	public KickPlayerViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayPlayerWithSuchNameNotFound(String name) {
		String message = KickPlayerViewMessages.KICK_PLAYER_PLAYER_WITH_SUCH_NAME_NOT_FOUND;
		message = message.replace("$player$", name);
		displayMessage(viewer, message);
	}

	@Override
	public void displayPlayerIsNotIngame() {
		displayMessage(viewer, KickPlayerViewMessages.KICK_PLAYER_PLAYER_IS_NOT_INGAME);
	}

	@Override
	public void displayPlayerSucccessfullyKicked() {
		displayMessage(viewer, KickPlayerViewMessages.KICK_PLAYER_PLAYER_SUCCESSFULLY_KICKED);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, KickPlayerViewMessages.KICK_PLAYER_NO_PERMISSION);
	}

	@Override
	public void displayKickMessage(UUID kickedPlayer, String reason) {
		String message = KickPlayerViewMessages.KICK_PLAYER_PLAYER_DISPLAY_KICK_MESSAGE;
		message = message.replace("$reason$", reason);
		displayMessage(kickedPlayer, message);
	}
	
	@Override
	public void displayPlayerKicked(List<UUID> viewers, String player) {
		String message = KickPlayerViewMessages.KICK_PLAYER_PLAYER_KICKED;
		message = message.replace("$player$", player);
		for (UUID viewer : viewers) {
			displayMessage(viewer, message);
		}
	}
	
}
