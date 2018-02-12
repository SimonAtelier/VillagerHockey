package usecases.LeaveTeam;

import java.util.UUID;

import usecases.LeaveTeam.LeaveTeam.LeaveTeamRequest;

public class LeaveTeamRequestModel implements LeaveTeamRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
