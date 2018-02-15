package usecases.SaveInventory;

import java.util.UUID;

import context.Context;
import usecases.SaveInventory.SaveInventory.SaveInventoryRequest;

public class SaveInventoryController {

	public void onSaveInventory(UUID player, boolean clear) {
		SaveInventory useCase = new SaveInventoryUseCase();
		useCase.setInventoryGateway(Context.inventoryGateway);
		useCase.execute(createRequest(player, clear));
	}
	
	private SaveInventoryRequest createRequest(UUID player, boolean clear) {
		SaveInventoryRequestModel requestModel = new SaveInventoryRequestModel();
		requestModel.setPlayer(player);
		requestModel.setClearInventoryAfterSaving(clear);
		return requestModel;
	}
	
}
