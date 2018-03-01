package usecases.showachievements;

import java.util.UUID;

import usecases.showachievements.ShowAchievements.ShowAchievementsRequest;

public class ShowAchievementsRequestModel implements ShowAchievementsRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}

}
