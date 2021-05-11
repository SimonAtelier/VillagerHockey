package usecases.api.saveinventory;

import java.util.UUID;

import usecases.api.saveinventory.SaveInventory.SaveInventoryRequest;

public class SaveInventoryRequestModel implements SaveInventoryRequest {

	private boolean clearInventoryAfterSaving;
	private UUID player;
	
	public boolean isClearInventoryAfterSaving() {
		return clearInventoryAfterSaving;
	}
	
	public void setClearInventoryAfterSaving(boolean clearInventoryAfterSaving) {
		this.clearInventoryAfterSaving = clearInventoryAfterSaving;
	}
	
	public UUID getPlayer() {
		return player;
	}
	
	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
