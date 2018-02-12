package usecases.PerformAction.ChangeFoodLevel;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class ChangeFoodLevelEventListener implements Listener {
	
	@EventHandler
	public void onFoodLevelChanged(FoodLevelChangeEvent e) {
		HumanEntity entity = e.getEntity();
		if (entity instanceof Player) {
			Player player = (Player) entity;
			ChangeFoodLevel useCase = new ChangeFoodLevelUseCase();
			e.setCancelled(!useCase.canChangeFoodLevel(player.getUniqueId()));
		}
	}

}
