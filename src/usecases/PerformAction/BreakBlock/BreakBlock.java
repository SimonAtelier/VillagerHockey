package usecases.PerformAction.BreakBlock;

import java.util.UUID;

public interface BreakBlock {

	boolean canBreakBlock(UUID uniquePlayerId);
	
}
