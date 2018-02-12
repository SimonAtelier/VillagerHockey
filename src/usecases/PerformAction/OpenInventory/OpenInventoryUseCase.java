package usecases.PerformAction.OpenInventory;

import java.util.UUID;

public class OpenInventoryUseCase implements OpenInventory {

	@Override
	public boolean canOpenInventory(UUID uniquePlayerId) {
//		return !Context.gameGateway.isIngame(uniquePlayerId);
		return true;
	}

}
