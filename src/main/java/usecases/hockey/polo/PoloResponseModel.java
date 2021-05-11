package usecases.hockey.polo;

import java.util.List;
import java.util.UUID;

import entities.Location;
import entities.TeamColor;

public class PoloResponseModel {

	private String worldName;
	private List<UUID> players;
	private List<Location> locations;
	private List<TeamColor> teamColors;

	public List<UUID> getPlayers() {
		return players;
	}

	public void setPlayers(List<UUID> players) {
		this.players = players;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String gameName) {
		this.worldName = gameName;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<TeamColor> getTeamColors() {
		return teamColors;
	}

	public void setTeamColors(List<TeamColor> teamColors) {
		this.teamColors = teamColors;
	}
	
}
