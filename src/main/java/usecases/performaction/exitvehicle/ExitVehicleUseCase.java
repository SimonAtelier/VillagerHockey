package usecases.performaction.exitvehicle;

import java.util.UUID;

import context.Context;

public class ExitVehicleUseCase implements ExitVehicle {

	@Override
	public boolean canExitVehicle(UUID uniquePlayerId) {
		if (!Context.gameGateway.isIngame(uniquePlayerId))
			return true;
			
		return (Context.gameGateway.findGameOfPlayer(uniquePlayerId).isCanLeaveVehicle());
	}

}
