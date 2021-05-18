package usecases.hockey.destroypinata;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import context.Context;

public class PinataListener implements Listener {
	
	@EventHandler
	public void on(EntityDeathEvent e) {
		Player player = e.getEntity().getKiller();
		
		if (player == null)
			return;
		
		if (!Context.gameGateway.isIngame(player.getUniqueId()))
			return;
		
		if (e.getEntityType() == EntityType.PIG) {
			new DestroyPinataController().onDestroyPinata(player.getUniqueId());
			e.setDroppedExp(0);
			e.getDrops().clear();
		}

	}

}
