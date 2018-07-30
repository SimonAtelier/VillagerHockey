package usecases.executecommand;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import context.Context;

public class PlayerCommandPreprocessListener implements Listener{

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		UUID uniquePlayerId = e.getPlayer().getUniqueId();

		if (!Context.gameGateway.isIngame(uniquePlayerId))
			return;

		String message = e.getMessage();

		if (!message.startsWith("vh"))
			e.setCancelled(true);
	}

}
