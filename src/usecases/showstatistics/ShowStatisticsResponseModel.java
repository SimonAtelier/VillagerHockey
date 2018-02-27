package usecases.showstatistics;

public class ShowStatisticsResponseModel {

	private int gamesPlayed;
	private int gamesDraw;
	private int gamesWon;
	private int gamesLost;
	private String player;
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getGamesDraw() {
		return gamesDraw;
	}
	
	public void setGamesDraw(int gamesDraw) {
		this.gamesDraw = gamesDraw;
	}
	
	public int getGamesWon() {
		return gamesWon;
	}
	
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	
	public int getGamesLost() {
		return gamesLost;
	}
	
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

}
