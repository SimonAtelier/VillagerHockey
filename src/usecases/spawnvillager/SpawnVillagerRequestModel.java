package usecases.spawnvillager;

import usecases.spawnvillager.SpawnVillager.SpawnVillagerRequest;

public class SpawnVillagerRequestModel implements SpawnVillagerRequest {
	
	private String game;

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}
