package usecases.PerformAction.PickupItem;

import java.util.UUID;

public interface PickupItem {

	boolean canPickupItem(UUID uniquePlayerId);
	
}
