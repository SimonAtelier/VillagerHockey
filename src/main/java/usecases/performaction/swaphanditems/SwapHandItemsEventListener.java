package usecases.performaction.swaphanditems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import context.Context;

public class SwapHandItemsEventListener implements Listener {

	@EventHandler
	public void onSwapHandItems(PlayerSwapHandItemsEvent e) {
		if (!Context.gameGateway.isIngame(e.getPlayer().getUniqueId()))
			return;
		e.setCancelled(true);
	}
	
}
