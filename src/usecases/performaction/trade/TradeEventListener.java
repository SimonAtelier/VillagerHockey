package usecases.performaction.trade;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import context.Context;
import gamestats.GameStatistic;
import gamestats.StatisticKeys;

public class TradeEventListener implements Listener {
	
	@EventHandler
	public void onTrade(InventoryOpenEvent e) {
		UUID uniquePlayerId = e.getPlayer().getUniqueId();
		if (canTrade(uniquePlayerId))
			return;
		
		if (e.getInventory().getType() == InventoryType.MERCHANT) {
			GameStatistic gameStatistic = Context.gameStatisticGateway.findByPlayerId(uniquePlayerId);
			gameStatistic.add(StatisticKeys.BAD_TRADES, 1);
			e.setCancelled(true);
		}
	}

	private boolean canTrade(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
