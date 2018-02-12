package usecases.DisplayWinner;

import usecases.DisplayWinner.DisplayWinner.DisplayWinnerRequest;

public class DisplayWinnerRequestModel implements DisplayWinnerRequest {

	private String game;

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}
	
}
