package usecases.performaction.damageitem;

import java.util.UUID;

import context.Context;

public class DamageItemUseCase implements DamageItem {

	@Override
	public boolean canDamageItem(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
