package gamestats;

import java.util.UUID;

public class GameStatistic {

	private int puckHits;
	private int scoredGoals;
	private String gameName;
	private UUID uniquePlayerId;

	public int getPuckHits() {
		return puckHits;
	}

	public void setPuckHits(int puckHits) {
		this.puckHits = puckHits;
	}

	public int getScoredGoals() {
		return scoredGoals;
	}

	public void setScoredGoals(int scoredGoals) {
		this.scoredGoals = scoredGoals;
	}

	public UUID getUniquePlayerId() {
		return uniquePlayerId;
	}

	public void setUniquePlayerId(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
