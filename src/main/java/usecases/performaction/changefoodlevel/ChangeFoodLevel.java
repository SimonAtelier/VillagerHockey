package usecases.performaction.changefoodlevel;

import java.util.UUID;

public interface ChangeFoodLevel {

	boolean canChangeFoodLevel(UUID uniquePlayerId);
	
}
