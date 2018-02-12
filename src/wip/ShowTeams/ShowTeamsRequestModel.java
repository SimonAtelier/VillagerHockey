package wip.ShowTeams;

import java.util.UUID;

import wip.ShowTeams.ShowTeams.ShowTeamsRequest;

public class ShowTeamsRequestModel implements ShowTeamsRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
