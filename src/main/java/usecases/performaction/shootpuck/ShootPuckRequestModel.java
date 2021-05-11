package usecases.performaction.shootpuck;

import java.util.UUID;

import usecases.performaction.shootpuck.ShootPuck.ShootPuckRequest;

public class ShootPuckRequestModel implements ShootPuckRequest {

	private UUID player;
	private String puckName;

	public UUID getPlayer() {
		return player;
	}

	public void setPlayer(UUID player) {
		this.player = player;
	}

	public String getPuckName() {
		return puckName;
	}

	public void setPuckName(String puckName) {
		this.puckName = puckName;
	}
	
}
