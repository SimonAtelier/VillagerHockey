package gamestats;

import java.util.UUID;

public interface StatsProvider {

	UUID getUniquePlayerId();
	
	int getValue(String key);
	
}
