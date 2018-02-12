package usecases.PerformAction.OpenInventory;

import java.util.UUID;

public interface OpenInventory {

	boolean canOpenInventory(UUID uniquePlayerId);
	
}
