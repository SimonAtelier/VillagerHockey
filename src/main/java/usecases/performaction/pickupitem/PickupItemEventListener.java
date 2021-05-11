package usecases.performaction.pickupitem;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupItemEventListener implements Listener {

	@EventHandler
	public void onEntityPickupItem(EntityPickupItemEvent e) {
		if (e.getEntityType() != EntityType.PLAYER)
			return;
		
		Player player = (Player) e.getEntity();
		PickupItem useCase = new PickupItemUseCase();
		e.setCancelled(!useCase.canPickupItem(player.getUniqueId()));
	}
	
}
