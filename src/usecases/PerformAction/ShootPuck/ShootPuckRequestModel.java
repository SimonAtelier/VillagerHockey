package usecases.PerformAction.ShootPuck;

import java.util.UUID;

import usecases.PerformAction.ShootPuck.ShootPuck.ShootPuckRequest;

public class ShootPuckRequestModel implements ShootPuckRequest {

	private UUID player;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}
	
}
