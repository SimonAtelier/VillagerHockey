package usecases.performaction.changefoodlevel;

import java.util.UUID;

import context.Context;

public class ChangeFoodLevelUseCase implements ChangeFoodLevel {

	@Override
	public boolean canChangeFoodLevel(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
