package usecases.performaction.exitvehicle;

import java.util.UUID;

import context.Context;
import game.Game;
import game.hockey.HockeyGameCycle;

public class ExitVehicleUseCase implements ExitVehicle {

	@Override
	public boolean canExitVehicle(UUID uniquePlayerId) {
		if (!Context.gameGateway.isIngame(uniquePlayerId))
			return true;
		
		Game game = Context.gameGateway.findGameOfPlayer(uniquePlayerId);
		HockeyGameCycle gameCycle = (HockeyGameCycle) game.getGameCycle();
		return (gameCycle.isCanLeaveVehicle());
	}

}
