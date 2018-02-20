package usecases.saveinventory;

import java.util.UUID;

import gateways.InventoryGateway;

public interface SaveInventory {

	void execute(SaveInventoryRequest request);
	
	void setInventoryGateway(InventoryGateway inventoryGateway);
	
	public interface SaveInventoryRequest {
		
		boolean isClearInventoryAfterSaving();
		
		UUID getPlayer();
		
	}
	
}
