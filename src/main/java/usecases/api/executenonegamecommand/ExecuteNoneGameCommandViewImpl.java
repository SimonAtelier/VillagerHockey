package usecases.api.executenonegamecommand;

import java.util.UUID;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import context.Context;
import minigame.view.MessageView;

public class ExecuteNoneGameCommandViewImpl implements ExecuteNoneGameCommandView {

	private UUID viewer;
	private PlayerCommandPreprocessEvent event;
	
	public ExecuteNoneGameCommandViewImpl(UUID viewer, PlayerCommandPreprocessEvent event) {
		this.viewer = viewer;
		this.event = event;
	}
	
	private void displayMessage(UUID viewer, String message) {
		MessageView messageView = Context.messageView;
		messageView.displayMessage(viewer, message);
	}
	
	@Override
	public void displayNoPermission() {
		event.setCancelled(true);
		displayMessage(viewer, ExecuteNoneGameCommandViewMessages.EXECUTE_NONE_GAME_COMMAND_NO_PERMISSION);
	}
	
}
