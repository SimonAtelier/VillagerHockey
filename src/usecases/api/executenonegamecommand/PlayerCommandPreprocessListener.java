package usecases.api.executenonegamecommand;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import context.Context;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		UUID uniquePlayerId = e.getPlayer().getUniqueId();
		String rawCommandMessage = e.getMessage();

		ExecuteNoneGameCommandUseCase useCase = new ExecuteNoneGameCommandUseCase();
		ExecuteNoneGameCommandView view = new ExecuteNoneGameCommandViewImpl(uniquePlayerId, e);
		ExecuteNoneGameCommandPresenter presenter = new ExecuteNoneGameCommandPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(uniquePlayerId, rawCommandMessage, presenter);
	}

}
