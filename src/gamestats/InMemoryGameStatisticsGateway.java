package gamestats;

import java.util.HashMap;
import java.util.UUID;

public class InMemoryGameStatisticsGateway implements GameStatisticGateway {

	private HashMap<String, UUID> lastHitters;
	private HashMap<UUID, GameStatistic> gameStatistics;
	
	public InMemoryGameStatisticsGateway() {
		gameStatistics = new HashMap<UUID, GameStatistic>();
		lastHitters = new HashMap<String, UUID>();
	}
	
	@Override
	public GameStatistic findByPlayerId(UUID uniquePlayerId) {
		return gameStatistics.get(uniquePlayerId);
	}

	@Override
	public void updateLastHitter(String gameName, UUID uniquePlayerId) {
		lastHitters.put(gameName, uniquePlayerId);
	}

	@Override
	public UUID findLastHitter(String gameName) {
		return lastHitters.get(gameName);
	}

	@Override
	public void removeStatisticsOfPlayer(UUID uniquePlayerId) {
		gameStatistics.remove(uniquePlayerId);
	}

	@Override
	public void createStatisticsForPlayer(String gameName, UUID uniquePlayerId) {
		GameStatistic gameStatistic = new GameStatistic();
		gameStatistic.setUniquePlayerId(uniquePlayerId);
		gameStatistic.setGameName(gameName);
		gameStatistics.put(uniquePlayerId, gameStatistic);
	}
	
}
