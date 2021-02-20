package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Teams {

	private List<Team> teams;

	public Teams() {
		teams = new ArrayList<Team>();
	}

	public void add(Team team) {
		teams.add(team);
	}

	public void removePlayer(UUID player) {
		Team team = findTeamOfPlayer(player);
		if (team != null)
			team.removePlayer(player);
	}

	public void resetTeamScores() {
		for (Team team : teams) {
			team.setScore(0);
		}
	}

	public Team findTeamByName(String name) {
		for (Team team : teams) {
			if (team.getName().equals(name)) {
				return team;
			}
		}
		return null;

	}

	public Team findTeamByColor(TeamColor color) {
		for (Team team : teams) {
			if (team.getColor().equals(color)) {
				return team;
			}
		}
		return null;
	}

	public Team findTeamOfPlayer(UUID player) {
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			if (team.containsPlayer(player)) {
				return team;
			}
		}
		return null;
	}

	public Team findLowestTeam() {
		Team lowestTeam = teams.get(0);
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			if (team.size() < lowestTeam.size()) {
				lowestTeam = team;
			}
		}
		return lowestTeam;
	}

	public Team findTeamWithHighestScore() {
		int highestScore = 0;
		Team teamWithHighestScore = null;
		for (Team team : teams) {
			int score = team.getScore();
			if (score >= highestScore) {
				highestScore = score;
				teamWithHighestScore = team;
			}
		}
		return teamWithHighestScore;
	}
	
	public Team findTeamWithLowestScore() {
		int lowestScore = teams.get(0).getScore();
		Team teamWithLowestScore = teams.get(0);
		for (Team team : teams) {
			int score = team.getScore();
			if (score <= lowestScore) {
				lowestScore = score;
				teamWithLowestScore = team;
			}
		}
		return teamWithLowestScore;
	}

	public List<Team> findAllTeams() {
		return new ArrayList<Team>(teams);
	}

	public boolean equalScores() {
		boolean equalScores = true;
		int score = teams.get(0).getScore();
		for (Team team : teams) {
			equalScores &= (score == team.getScore());
		}
		return equalScores;
	}

	public boolean containsTeamWithName(String name) {
		return findTeamByName(name) != null;
	}

	public boolean containsTeamWithColor(TeamColor color) {
		return findTeamByColor(color) != null;
	}

	public int getMaximumAmountOfPlayers() {
		int maximumAmount = 0;
		for (Team team : teams) {
			maximumAmount += team.getMaximumSize();
		}
		return maximumAmount;
	}

	public int getNumberOfTeams() {
		return teams.size();
	}

}
