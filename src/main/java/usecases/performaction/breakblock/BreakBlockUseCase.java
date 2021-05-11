package usecases.performaction.breakblock;

import java.util.UUID;

import context.Context;

public class BreakBlockUseCase implements BreakBlock {

	@Override
	public boolean canBreakBlock(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
