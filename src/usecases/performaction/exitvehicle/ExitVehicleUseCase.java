package usecases.performaction.exitvehicle;

import java.util.UUID;

import context.Context;

public class ExitVehicleUseCase implements ExitVehicle {

	@Override
	public boolean canExitVehicle(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
