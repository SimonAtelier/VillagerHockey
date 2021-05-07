package usecases.boatboogie;

import usecases.boatboogie.BoatBoogie.BoatBoogieRequest;

public class BoatBoogieRequestModel implements BoatBoogieRequest {

	private String gameName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
