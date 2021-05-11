package usecases.api.updatejoinsigns;

import usecases.api.updatejoinsigns.UpdateJoinSigns.UpdateJoinSignsRequest;

public class UpdateJoinSignsRequestModel implements UpdateJoinSignsRequest {

	private int playersCount;
	private int maximumAmountOfPlayers;
	private String game;
	private String gameState;
	
	public int getPlayersCount() {
		return playersCount;
	}
	
	public void setPlayersCount(int playersCount) {
		this.playersCount = playersCount;
	}
	
	public int getMaximumAmountOfPlayers() {
		return maximumAmountOfPlayers;
	}
	
	public void setMaximumAmountOfPlayers(int maximumAmountOfPlayers) {
		this.maximumAmountOfPlayers = maximumAmountOfPlayers;
	}
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getGameState() {
		return gameState;
	}
	
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	
}
