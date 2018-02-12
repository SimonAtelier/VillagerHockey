package usecases.PerformAction.DropItem;

import java.util.UUID;

public interface DropItem {

	boolean canDropItem(UUID uniquePlayerId);
	
}
