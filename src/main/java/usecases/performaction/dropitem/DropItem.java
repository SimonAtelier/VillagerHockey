package usecases.performaction.dropitem;

import java.util.UUID;

public interface DropItem {

	boolean canDropItem(UUID uniquePlayerId);
	
}
