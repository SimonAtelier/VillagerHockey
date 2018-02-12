package usecases.JoinGame;

import java.util.UUID;

import usecases.JoinGame.JoinGame.JoinGameRequest;

public class JoinGameRequestModel implements JoinGameRequest {
	
	private String game;
	
	private UUID uniquePlayerId;

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

}
