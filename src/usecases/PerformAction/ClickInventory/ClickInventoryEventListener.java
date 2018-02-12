package usecases.PerformAction.ClickInventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickInventoryEventListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		HumanEntity entity = e.getWhoClicked();
		if (entity instanceof Player) {
			Player player = (Player) entity;
			ClickInventory useCase = new ClickInventoryUseCase();
			e.setCancelled(!useCase.canClickInventory(player.getUniqueId()));
		}
	}

}
