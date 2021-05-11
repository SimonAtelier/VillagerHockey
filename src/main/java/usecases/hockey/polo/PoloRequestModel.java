package usecases.hockey.polo;

import usecases.hockey.polo.Polo.PoloRequest;

public class PoloRequestModel implements PoloRequest {

	private String gameName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
}
