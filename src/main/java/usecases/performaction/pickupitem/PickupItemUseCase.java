package usecases.performaction.pickupitem;

import java.util.UUID;

import context.Context;

public class PickupItemUseCase implements PickupItem {

	@Override
	public boolean canPickupItem(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
