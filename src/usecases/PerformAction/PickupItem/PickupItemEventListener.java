package usecases.PerformAction.PickupItem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupItemEventListener implements Listener {

	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		PickupItem useCase = new PickupItemUseCase();
		e.setCancelled(!useCase.canPickupItem(e.getPlayer().getUniqueId()));
	}
	
}
