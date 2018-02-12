package usecases.PerformAction.DropItem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemEventListener implements Listener {

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		DropItem useCase = new DropItemUseCase();
		e.setCancelled(!useCase.canDropItem(e.getPlayer().getUniqueId()));
	}

}
