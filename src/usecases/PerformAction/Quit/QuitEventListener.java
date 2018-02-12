package usecases.PerformAction.Quit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import context.Context;
import usecases.LeaveGame.LeaveGame;
import usecases.LeaveGame.LeaveGame.LeaveGameResponse;
import usecases.LeaveGame.LeaveGamePresenter;
import usecases.LeaveGame.LeaveGameUseCase;
import usecases.LeaveGame.LeaveGameView;
import usecases.LeaveGame.LeaveGameViewImpl;

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
