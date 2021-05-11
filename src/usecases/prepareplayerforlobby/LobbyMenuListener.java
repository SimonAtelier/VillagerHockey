package usecases.prepareplayerforlobby;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import context.Context;
import usecases.api.forcestart.ForceStartCommand;
import usecases.api.leavegame.LeaveGameCommand;
import usecases.displayachievements.DisplayAchievementsController;

public class LobbyMenuListener implements Listener {
	
	private PlayerInteractEvent event;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		this.event = event;
		
		if (!isRightClick())
			return;
		
		if (playerIsNotIngame())
			return;
		
		if (noItemClicked())
			return;

		handleRightClick(event.getItem().getType(), event.getPlayer().getUniqueId());
		
		event.setCancelled(true);
	}
	
	private boolean noItemClicked() {
		return event.getItem() == null;
	}
	
	private boolean isRightClick() {
		return event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;
	}
	
	private boolean playerIsNotIngame() {
		return !Context.gameGateway.isIngame(event.getPlayer().getUniqueId());
	}
	
	private void handleRightClick(Material material, UUID player) {
		switch (material) {
		case SLIME_BALL:
			onLeaveGame(player);
			break;
		case DIAMOND:
			onForceStart(player);
			break;
		case NETHER_STAR:
			onDisplayAchievements(player);
			break;
		default:
			break;
		}
	}
	
	private void onDisplayAchievements(UUID player) {
		new DisplayAchievementsController().onDisplayAchievements(player);
	}
	
	private void onLeaveGame(UUID player) {
		LeaveGameCommand command = new LeaveGameCommand();
		command.execute(player, new ArrayList<String>());
	}
	
	private void onForceStart(UUID player) {
		ForceStartCommand command = new ForceStartCommand();
		command.execute(player, new ArrayList<String>());
	}

}
