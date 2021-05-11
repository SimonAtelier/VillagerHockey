package usecases.hockey.pinata;

import usecases.hockey.pinata.Pinata.PinataRequest;

public class PinataRequestModel implements PinataRequest {
	
	private String gameName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
