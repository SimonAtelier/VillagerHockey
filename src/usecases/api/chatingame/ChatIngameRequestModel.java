package usecases.api.chatingame;

import java.util.UUID;

import usecases.api.chatingame.ChatIngame.ChatIngameRequest;

public class ChatIngameRequestModel implements ChatIngameRequest {
	
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
