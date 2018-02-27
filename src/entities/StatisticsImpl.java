package entities;

public class StatisticsImpl implements Statistics {

	private int gamesPlayed;
	private int gamesWon;
	private int gamesDraw;
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getGamesWon() {
		return gamesWon;
	}
	
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	
	public int getGamesDraw() {
		return gamesDraw;
	}
	
	public void setGamesDraw(int gamesDraw) {
		this.gamesDraw = gamesDraw;
	}

	public int getGamesLost() {
		return gamesPlayed - gamesWon - gamesDraw;
	}
	
}
