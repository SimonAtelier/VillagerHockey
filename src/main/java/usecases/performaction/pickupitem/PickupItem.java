package usecases.performaction.pickupitem;

import java.util.UUID;

public interface PickupItem {

	boolean canPickupItem(UUID uniquePlayerId);
	
}
