package usecases.performaction.shootpuck;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import context.Context;
import usecases.performaction.shootpuck.ShootPuck.ShootPuckResponse;

public class ShootPuckEventListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		Entity entity = e.getEntity();

		if (!(entity instanceof Villager))
			return;

		if (!(damager instanceof Player))
			return;
		
		Player player = (Player) damager;

		if (!Context.gameGateway.isIngame(player.getUniqueId()))
			return;
		
		e.setDamage(0);

		ShootPuckRequestModel requestModel = new ShootPuckRequestModel();
		requestModel.setPlayer(player.getUniqueId());
		requestModel.setPuckName(entity.getCustomName());

		ShootPuck useCase = new ShootPuckUseCase();
		ShootPuckView view = new ShootPuckViewImpl();
		ShootPuckResponse presenter = new ShootPuckPresenter(view);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setGameStatisticsGateway(Context.gameStatisticGateway);
		useCase.execute(requestModel, presenter);
	}

}
