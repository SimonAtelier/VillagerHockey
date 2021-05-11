package entities;

public class StatisticsImpl implements Statistics {

	private int gamesPlayed;
	private int gamesWon;
	private int gamesDraw;
	private int gamesLost;
	private long totalTimePlayedInSeconds;
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	@Override
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	@Override
	public int getGamesWon() {
		return gamesWon;
	}
	
	@Override
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	
	@Override
	public int getGamesDraw() {
		return gamesDraw;
	}
	
	@Override
	public void setGamesDraw(int gamesDraw) {
		this.gamesDraw = gamesDraw;
	}

	@Override
	public int getGamesLost() {
		return gamesLost;
	}
	
	@Override
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}

	@Override
	public long getTotalTimePlayedInSeconds() {
		return totalTimePlayedInSeconds;
	}

	@Override
	public void setTotalTimePlayedInSeconds(long totalTimePlayedInSeconds) {
		this.totalTimePlayedInSeconds = totalTimePlayedInSeconds;
	}
	
}
