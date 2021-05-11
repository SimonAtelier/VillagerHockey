package usecases.performaction.clickjoinsign;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickJoinSignEventListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		
		if (!(e.getClickedBlock().getState() instanceof Sign))
			return;
		
		Sign sign = (Sign) e.getClickedBlock().getState();
		
		if (!sign.getLine(0).equals("[vh]"))
			return;
		
		Bukkit.dispatchCommand(e.getPlayer(), "vh join " + sign.getLine(1));
	}
	
}
