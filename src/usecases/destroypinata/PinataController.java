package usecases.destroypinata;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import context.Context;

public class PinataController implements Listener {
	
	@EventHandler
	public void on(EntityDeathEvent e) {
		Player player = e.getEntity().getKiller();
		
		if (!Context.gameGateway.isIngame(player.getUniqueId()))
			return;
		
		new DestroyPinataController().onDestroyPinata(player.getUniqueId());
		
		if (e.getEntityType() == EntityType.PIG) {
			e.setDroppedExp(0);
			e.getDrops().clear();
		}

	}

}
