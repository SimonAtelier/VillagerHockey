package usecases.performaction.quit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import context.Context;
import usecases.api.leavegame.LeaveGame;
import usecases.api.leavegame.LeaveGamePresenter;
import usecases.api.leavegame.LeaveGameUseCase;
import usecases.api.leavegame.LeaveGameView;
import usecases.api.leavegame.LeaveGameViewImpl;
import usecases.api.leavegame.LeaveGame.LeaveGameResponse;

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
