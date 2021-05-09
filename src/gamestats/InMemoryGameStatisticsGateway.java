package gamestats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InMemoryGameStatisticsGateway implements GameStatisticGateway, PropertyChangeListener {

	private HashMap<String, UUID> lastHitters;
	private HashMap<UUID, GameStatistic> gameStatistics;
	private List<PropertyChangeListener> listeners;
	
	public InMemoryGameStatisticsGateway() {
		gameStatistics = new HashMap<UUID, GameStatistic>();
		lastHitters = new HashMap<String, UUID>();
		listeners = new ArrayList<PropertyChangeListener>();
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
		GameStatistic gameStatistic = gameStatistics.get(uniquePlayerId);
		gameStatistic.removePropertyChangeListener(this);
		gameStatistics.remove(uniquePlayerId);
	}

	@Override
	public void createStatisticsForPlayer(String gameName, UUID uniquePlayerId) {
		GameStatistic gameStatistic = new GameStatsYaml().load(uniquePlayerId);
		gameStatistic.addPropertyChangeListener(this);
		gameStatistics.put(uniquePlayerId, gameStatistic);
	}

	@Override
	public void onPropertyChanged(StatsProvider statsProvider) {
		firePropertyChanged(statsProvider);
	}
	
	private void firePropertyChanged(StatsProvider statsProvider) {
		for (PropertyChangeListener listener : listeners) {
			listener.onPropertyChanged(statsProvider);
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.remove(listener);
	}
	
}
