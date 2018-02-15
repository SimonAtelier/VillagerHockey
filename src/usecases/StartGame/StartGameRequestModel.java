package usecases.StartGame;

import java.util.UUID;

import usecases.StartGame.StartGame.StartGameRequest;

public class StartGameRequestModel implements StartGameRequest {

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
