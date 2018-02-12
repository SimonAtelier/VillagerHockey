package usecases.PerformAction.DropItem;

import java.util.UUID;

import context.Context;

public class DropItemUseCase implements DropItem {

	@Override
	public boolean canDropItem(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
