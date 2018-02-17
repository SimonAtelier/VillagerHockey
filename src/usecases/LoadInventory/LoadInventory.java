package usecases.LoadInventory;

import java.util.UUID;

import gateways.InventoryGateway;

public interface LoadInventory {

	void execute(UUID player);

	void setInventoryGateway(InventoryGateway inventoryGateway);
		
}
