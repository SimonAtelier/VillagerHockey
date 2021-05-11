package gamestats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GameStatistic implements StatsProvider {
	
	private UUID uniquePlayerId;
	private HashMap<String, Integer> properties;
	private List<PropertyChangeListener> listeners;
	
	public GameStatistic() {
		properties = new HashMap<String, Integer>();
		listeners = new ArrayList<PropertyChangeListener>();
	}

	public void setValue(String key, int value) {
		properties.put(key, value);
		firePropertyChanged();
	}
	
	public int getValue(String key) {
		if (!properties.containsKey(key))
			properties.put(key, 0);
		return properties.get(key);
	}
	
	public boolean hasPropertyWithKey(String key) {
		return properties.containsKey(key);
	}
	
	public void add(String key, int value) {
		if (!hasPropertyWithKey(key)) {
			properties.put(key, value);
			firePropertyChanged();
			return;
		}
		setValue(key, value + getValue(key));
	}
	
	private void firePropertyChanged() {
		for (PropertyChangeListener listener : listeners)
			listener.onPropertyChanged(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.remove(listener);
	}

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}
	
	public Set<String> getKeys() {
		return properties.keySet();
	}

}
