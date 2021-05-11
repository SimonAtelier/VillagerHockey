package usecases.performaction.damageitem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class DamageItemEventListener implements Listener {
	
	@EventHandler
	public void onPlayerItemDamage(PlayerItemDamageEvent e) {
		Player player = e.getPlayer();
		DamageItem useCase = new DamageItemUseCase();
		e.setCancelled(!useCase.canDamageItem(player.getUniqueId()));
	}
	
}
