package usecases.PerformAction.ConsumeItem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ConsumeItemEventListener implements Listener {

	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
		ConsumeItem useCase = new ConsumeItemUseCase();
		e.setCancelled(!useCase.canConsumeItem(e.getPlayer().getUniqueId()));
	}

}
