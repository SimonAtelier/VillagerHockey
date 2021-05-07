package usecases.pinata;

import usecases.pinata.Pinata.PinataRequest;

public class PinataRequestModel implements PinataRequest {
	
	private String gameName;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
