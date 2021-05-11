package usecases.api.loadinventory;

import java.util.UUID;

import context.Context;

public class LoadInventoryController {

	public void onLoadInventory(UUID player) {
		LoadInventory useCase = new LoadInventoryUseCase();
		useCase.setInventoryGateway(Context.inventoryGateway);
		useCase.execute(player);
	}
	
}
