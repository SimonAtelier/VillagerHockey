package usecases.api.chatingame;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEventListener implements Listener {
	
	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
		UUID player = e.getPlayer().getUniqueId();
		String message = e.getMessage();
		e.setCancelled(new ChatIngameController().handleChatIngame(player, message));
	}

}
