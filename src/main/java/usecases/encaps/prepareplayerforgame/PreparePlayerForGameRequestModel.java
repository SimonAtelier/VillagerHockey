package usecases.encaps.prepareplayerforgame;

import java.util.UUID;

import usecases.encaps.prepareplayerforgame.PreparePlayerForGame.PreparePlayerForGameRequest;

public class PreparePlayerForGameRequestModel implements PreparePlayerForGameRequest {

	private String gameName;
	private UUID uniquePlayerId;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

}
