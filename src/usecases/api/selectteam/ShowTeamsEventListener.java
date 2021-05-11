package usecases.api.selectteam;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import usecases.api.showteams.ShowTeamsController;

public class ShowTeamsEventListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!isRightClick(e))
			return;

		if (!isItemValid(e))
			return;

		Player player = e.getPlayer();
		e.setCancelled(true);

		onShowTeam(player.getUniqueId());
	}

	private void onShowTeam(UUID uniquePlayerId) {
		new ShowTeamsController().onShowTeams(uniquePlayerId);
	}

	private boolean isItemValid(PlayerInteractEvent e) {
		if (e.getItem() == null)
			return false;
		return e.getItem().getType() == Material.LEATHER_HELMET;
	}

	private boolean isRightClick(PlayerInteractEvent e) {
		return e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK;
	}

}
