package gamestats;

import java.util.UUID;

public interface GameStatisticGateway {
	
	GameStatistic findByPlayerId(UUID uniquePlayerId);
	
	void updateLastHitter(String gameName, UUID lastHitter);
	
	UUID findLastHitter(String gameName);
	
	void removeStatisticsOfPlayer(UUID uniquePlayerId);
	
	void createStatisticsForPlayer(UUID uniquePlayerId);
	
}
