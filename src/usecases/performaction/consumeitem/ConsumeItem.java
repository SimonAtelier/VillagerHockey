package usecases.performaction.consumeitem;

import java.util.UUID;

public interface ConsumeItem {

	boolean canConsumeItem(UUID uniquePlayerId);
	
}
