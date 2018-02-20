package usecases.performaction.quit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import context.Context;
import usecases.leavegame.LeaveGame;
import usecases.leavegame.LeaveGamePresenter;
import usecases.leavegame.LeaveGameUseCase;
import usecases.leavegame.LeaveGameView;
import usecases.leavegame.LeaveGameViewImpl;
import usecases.leavegame.LeaveGame.LeaveGameResponse;

public class QuitEventListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		if (!Context.gameGateway.isIngame(player.getUniqueId()))
			return;
		
		LeaveGame useCase = new LeaveGameUseCase();
		LeaveGameView view = new LeaveGameViewImpl(player.getUniqueId());
		LeaveGameResponse presenter = new LeaveGamePresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(player.getUniqueId(), presenter);
	}
	
}
