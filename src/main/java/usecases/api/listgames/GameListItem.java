package usecases.api.listgames;

public class GameListItem {

	private int playersCount;
	private int maxPlayers;
	private String gameName;
	private String gameState;
	
	public int getPlayersCount() {
		return playersCount;
	}

	public void setPlayersCount(int playersCount) {
		this.playersCount = playersCount;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public String getGameState() {
		return gameState;
	}
	
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	
}
