package usecases.PerformAction.ChangeFoodLevel;

import java.util.UUID;

public interface ChangeFoodLevel {

	boolean canChangeFoodLevel(UUID uniquePlayerId);
	
}
