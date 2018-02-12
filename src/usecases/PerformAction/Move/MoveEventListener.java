package usecases.PerformAction.Move;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import context.Context;

public class MoveEventListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Move useCase = new MoveUseCase();
		useCase.setGameGateway(Context.gameGateway);
		e.setCancelled(!useCase.canMove(player.getUniqueId()));
	}
}
