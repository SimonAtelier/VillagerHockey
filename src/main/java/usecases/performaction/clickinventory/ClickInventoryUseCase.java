package usecases.performaction.clickinventory;

import java.util.UUID;

import context.Context;

public class ClickInventoryUseCase implements ClickInventory {

	@Override
	public boolean canClickInventory(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
