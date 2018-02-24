package usecases.chatwithteam;

import java.util.UUID;

import usecases.chatwithteam.ChatWithTeam.ChatWithTeamRequest;

public class ChatWithTeamRequestModel implements ChatWithTeamRequest {
	
	private UUID player;
	private String message;
	
	public UUID getPlayer() {
		return player;
	}
	
	public void setPlayer(UUID player) {
		this.player = player;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
