package usecases.api.jointeam;

import java.util.UUID;

import usecases.api.jointeam.JoinTeam.JoinTeamRequest;

public class JoinTeamRequestModel implements JoinTeamRequest {

	private String team;
	private String game;
	private UUID player;
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public UUID getPlayer() {
		return player;
	}
	
	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
