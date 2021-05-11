package usecases.api.stopgame;

import java.util.UUID;

import usecases.api.stopgame.StopGame.StopGameRequest;

public class StopGameRequestModel implements StopGameRequest {
	
	private UUID player;
	private String game;
	
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

}
