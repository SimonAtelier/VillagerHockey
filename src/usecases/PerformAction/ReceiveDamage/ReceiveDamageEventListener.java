package usecases.PerformAction.ReceiveDamage;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import context.Context;

public class ReceiveDamageEventListener implements Listener {
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		
		if (!(entity instanceof Player))
			return;
		
		Player player = (Player) entity;
		
		if (isIngame(player.getUniqueId())) {
			e.setDamage(0);
		}
	}
	
	private boolean isIngame(UUID player) {
		return Context.gameGateway.isIngame(player);
	}

}
