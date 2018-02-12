package usecases.PerformAction.ConsumeItem;

import java.util.UUID;

public interface ConsumeItem {

	boolean canConsumeItem(UUID uniquePlayerId);
	
}
