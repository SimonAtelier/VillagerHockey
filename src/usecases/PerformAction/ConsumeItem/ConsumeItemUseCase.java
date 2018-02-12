package usecases.PerformAction.ConsumeItem;

import java.util.UUID;

import context.Context;

public class ConsumeItemUseCase implements ConsumeItem {

	@Override
	public boolean canConsumeItem(UUID uniquePlayerId) {
		return !Context.gameGateway.isIngame(uniquePlayerId);
	}

}
