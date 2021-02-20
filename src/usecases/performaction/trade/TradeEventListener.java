package usecases.performaction.trade;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.TradeSelectEvent;

import context.Context;

public class TradeEventListener implements Listener {

	@EventHandler
	public void onTrade(TradeSelectEvent e) {
		UUID uniquePlayerId = e.getWhoClicked().getUniqueId();
		if (!canTrade(uniquePlayerId)) {
			e.setCancelled(true);
		}
	}
	
	private boolean canTrade(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}
	
}

