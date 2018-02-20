package usecases.performaction.openinventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import context.Context;

public class OpenInventoryEventListener implements Listener {

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e) {
		HumanEntity entity = e.getPlayer();
		if (!(entity instanceof Player))
			return;
		Player player = (Player) entity;
		OpenInventory useCase = new OpenInventoryUseCase();
		useCase.setGameGateway(Context.gameGateway);
		e.setCancelled(!useCase.canOpenInventory(player.getUniqueId()));
	}

}
