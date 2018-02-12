package usecases.PerformAction.BreakBlock;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlockEventListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		BreakBlock useCase = new BreakBlockUseCase();
		e.setCancelled(!useCase.canBreakBlock(player.getUniqueId()));
	}

}
