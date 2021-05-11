package usecases.performaction.clickinventory;

import java.util.UUID;

public interface ClickInventory {

	boolean canClickInventory(UUID uniquePlayerId);
	
}
