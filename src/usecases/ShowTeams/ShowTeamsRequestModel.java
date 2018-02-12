package usecases.ShowTeams;

import java.util.UUID;

import usecases.ShowTeams.ShowTeams.ShowTeamsRequest;

public class ShowTeamsRequestModel implements ShowTeamsRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
