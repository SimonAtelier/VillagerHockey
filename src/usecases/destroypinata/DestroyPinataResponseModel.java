package usecases.destroypinata;

import java.util.List;
import java.util.UUID;

public class DestroyPinataResponseModel {

	private int points;
	private String destroyerName;
	private String destroyerTeamName;
	private List<UUID> players;
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getDestroyerName() {
		return destroyerName;
	}

	public void setDestroyerName(String destroyerName) {
		this.destroyerName = destroyerName;
	}

	public String getDestroyerTeamName() {
		return destroyerTeamName;
	}

	public void setDestroyerTeamName(String destroyerTeamName) {
		this.destroyerTeamName = destroyerTeamName;
	}

	public List<UUID> getPlayers() {
		return players;
	}

	public void setPlayers(List<UUID> players) {
		this.players = players;
	}

}
