package usecases.performaction.exitvehicle;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class ExitVehicleEventListener implements Listener {

	@EventHandler
	public void onPlayerDropItem(VehicleExitEvent e) {
		ExitVehicle useCase = new ExitVehicleUseCase();
		e.setCancelled(!useCase.canExitVehicle(e.getExited().getUniqueId()));
	}

}
