package usecases.performaction.exitvehicle;

import java.util.UUID;

public interface ExitVehicle {
	
	boolean canExitVehicle(UUID uniquePlayerId);

}
