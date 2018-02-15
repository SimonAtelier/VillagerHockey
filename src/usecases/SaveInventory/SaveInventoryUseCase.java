package usecases.SaveInventory;

import gateways.InventoryGateway;

public class SaveInventoryUseCase implements SaveInventory {

	private SaveInventoryRequest request;
	private InventoryGateway inventoryGateway;

	@Override
	public void execute(SaveInventoryRequest request) {
		setRequest(request);
		saveInventory();
		if (shouldClearInventory())
			clearInventory();
	}

	private void saveInventory() {
		inventoryGateway.save(request.getPlayer());
	}

	private void clearInventory() {
		inventoryGateway.clearInventoryOfPlayer(request.getPlayer());
	}

	private boolean shouldClearInventory() {
		return request.isClearInventoryAfterSaving();
	}

	private void setRequest(SaveInventoryRequest request) {
		this.request = request;
	}

	@Override
	public void setInventoryGateway(InventoryGateway inventoryGateway) {
		this.inventoryGateway = inventoryGateway;
	}

}
