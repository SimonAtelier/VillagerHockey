package usecases.api.showteams;

import java.util.UUID;

import usecases.api.showteams.ShowTeams.ShowTeamsRequest;

public class ShowTeamsRequestModel implements ShowTeamsRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
