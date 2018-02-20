package usecases.leaveteam;

import java.util.UUID;

import usecases.leaveteam.LeaveTeam.LeaveTeamRequest;

public class LeaveTeamRequestModel implements LeaveTeamRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
