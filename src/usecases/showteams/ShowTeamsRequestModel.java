package usecases.showteams;

import java.util.UUID;

import usecases.showteams.ShowTeams.ShowTeamsRequest;

public class ShowTeamsRequestModel implements ShowTeamsRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
