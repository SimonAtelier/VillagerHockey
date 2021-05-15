package usecases.hockey.boatboogie;

import java.util.List;
import java.util.UUID;

import entities.Location;

public class BoatBoogieResponseModel {

	private List<UUID> players;
	private List<Location> locations;

	public List<UUID> getPlayers() {
		return players;
	}

	public void setPlayers(List<UUID> players) {
		this.players = players;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}
