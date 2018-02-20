package usecases.displayteamscored;

import usecases.displayteamscored.DisplayTeamScored.DisplayTeamScoredRequest;

public class DisplayTeamScoreRequestModel implements DisplayTeamScoredRequest {

	private String game;
	private String team;
	
	public String getGame() {
		return game;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
}
