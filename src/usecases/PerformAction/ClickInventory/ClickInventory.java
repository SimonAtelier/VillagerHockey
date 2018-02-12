package usecases.PerformAction.ClickInventory;

import java.util.UUID;

public interface ClickInventory {

	boolean canClickInventory(UUID uniquePlayerId);
	
}
