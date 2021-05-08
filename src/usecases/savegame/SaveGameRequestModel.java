package usecases.savegame;

import java.util.UUID;

import usecases.savegame.SaveGame.SaveGameRequest;

public class SaveGameRequestModel implements SaveGameRequest {

	private UUID uniquePlayerId;
	private String gameName;

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
