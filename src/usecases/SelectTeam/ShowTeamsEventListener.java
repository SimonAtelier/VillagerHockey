package usecases.SelectTeam;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import context.Context;
import usecases.ShowTeams.ShowTeams;
import usecases.ShowTeams.ShowTeamsPresenter;
import usecases.ShowTeams.ShowTeamsRequestModel;
import usecases.ShowTeams.ShowTeamsUseCase;
import usecases.ShowTeams.ShowTeams.ShowTeamsResponse;

public class ShowTeamsEventListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!isRightClick(e))
			return;
		
		if (!isItemValid(e))
			return;
		
		Player player = e.getPlayer();
		e.setCancelled(true);
		
		executeShowTeamsUseCase(player.getUniqueId());
	}
	
	private void executeShowTeamsUseCase(UUID uniquePlayerId) {
		ShowTeamsRequestModel requestModel = new ShowTeamsRequestModel();
		requestModel.setPlayer(uniquePlayerId);
		
		SelectTeamView view = new SelectTeamViewImpl(uniquePlayerId);
		view.setTeamSelectListener(new SelectTeamController());
		
		ShowTeamsResponse presenter = new ShowTeamsPresenter(view);
		
		ShowTeams useCase = new ShowTeamsUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(requestModel, presenter);
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
