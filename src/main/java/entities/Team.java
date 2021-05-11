package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {

	private int score;
	private String name;
	private TeamColor color;
	private List<UUID> players;
	private List<Location> spawnLocations;

	public Team(String name, TeamColor color) {
		this.name = name;
		this.color = color;
		players = new ArrayList<UUID>();
		spawnLocations = new ArrayList<Location>();
	}

	public void addPlayer(UUID player) {
		if (player == null)
			return;
		if (containsPlayer(player))
			return;
		players.add(player);
	}

	public void removePlayer(UUID player) {
		players.remove(player);
	}

	public boolean containsPlayer(UUID player) {
		return players.contains(player);
	}

	public int size() {
		return players.size();
	}

	public int getMaximumSize() {
		return spawnLocations.size();
	}

	public void addSpawnLocation(Location location) {
		if (location == null)
			return;
		spawnLocations.add(location);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TeamColor getColor() {
		return color;
	}

	public void setColor(TeamColor color) {
		this.color = color;
	}

	public List<UUID> getPlayers() {
		return new ArrayList<UUID>(players);
	}

	public List<Location> getSpawnLocations() {
		return new ArrayList<Location>(spawnLocations);
	}

}
