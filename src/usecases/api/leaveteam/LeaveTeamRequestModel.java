package usecases.api.leaveteam;

import java.util.UUID;

import usecases.api.leaveteam.LeaveTeam.LeaveTeamRequest;

public class LeaveTeamRequestModel implements LeaveTeamRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
