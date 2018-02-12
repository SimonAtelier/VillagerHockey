package usecases.PerformAction.BreakJoinSign;

import java.util.UUID;

import org.bukkit.event.block.BlockBreakEvent;

import context.Context;
import view.MessageView;

public class BreakJoinSignViewImpl implements BreakJoinSignView {

	private UUID viewer;
	private BlockBreakEvent event;
	
	public BreakJoinSignViewImpl(UUID viewer, BlockBreakEvent event) {
		this.viewer = viewer;
		this.event = event;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		displayMessage(viewer, BreakJoinSignViewMessages.BREAK_JOIN_SIGN_NO_PERMISSION);
	}

	@Override
	public void displayNoSuchGame(String game) {
		String message = BreakJoinSignViewMessages.BREAK_JOIN_SIGN_NO_SUCH_GAME;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void displaySuccessfullyRemove(String game) {
		String message = BreakJoinSignViewMessages.BREAK_JOIN_SIGN_SUCCESS;
		message = message.replace("$game$", game);
		displayMessage(viewer, message);
	}

	@Override
	public void discardBreaking() {
		event.setCancelled(true);
	}

}
