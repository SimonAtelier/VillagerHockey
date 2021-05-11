package entities;

public interface Statistics {

	long getTotalTimePlayedInSeconds();
	
	void setTotalTimePlayedInSeconds(long totalTimePlayedInSeconds);
	
	int getGamesPlayed();
	
	int getGamesWon();
	
	int getGamesDraw();
	
	int getGamesLost();
	
	void setGamesPlayed(int gamesPlayed);
	
	void setGamesWon(int gamesWon);
	
	void setGamesDraw(int gamesDraw);
	
	void setGamesLost(int gamesLost);
	
}
