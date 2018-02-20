package usecases.setplayingtime;

import java.util.UUID;

import usecases.setplayingtime.SetPlayingTime.SetPlayingTimeRequest;

public class SetPlayingTimeRequestModel implements SetPlayingTimeRequest {

	private UUID player;
	private String game;
	private String playingTime;
	
	public UUID getPlayer() {
		return player;
	}
	
	public void setPlayer(UUID player) {
		this.player = player;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getPlayingTime() {
		return playingTime;
	}
	
	public void setPlayingTime(String playingTime) {
		this.playingTime = playingTime;
	}
	
}
