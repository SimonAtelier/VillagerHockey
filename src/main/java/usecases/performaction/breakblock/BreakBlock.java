package usecases.performaction.breakblock;

import java.util.UUID;

public interface BreakBlock {

	boolean canBreakBlock(UUID uniquePlayerId);
	
}
