package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Teams {

	private static final Object LIST_LOCK = new Object();

	private List<Team> teams;

	public Teams() {
		teams = new ArrayList<Team>();
	}

	public void add(Team team) {
		synchronized (LIST_LOCK) {
			teams.add(team);
		}
	}

	public void resetTeamScores() {
		synchronized (LIST_LOCK) {
			for (Team team : teams) {
				team.setScore(0);
			}
		}
	}

	public Team findTeamByName(String name) {
		synchronized (LIST_LOCK) {
			for (Team team : teams) {
				if (team.getName().equals(name)) {
					return team;
				}
			}
			return null;
		}
	}

	public Team findTeamByColor(TeamColor color) {
		synchronized (LIST_LOCK) {
			for (Team team : teams) {
				if (team.getColor().equals(color)) {
					return team;
				}
			}
			return null;
		}
	}

	public Team findTeamOfPlayer(UUID player) {
		synchronized (LIST_LOCK) {
			for (int i = 0; i < teams.size(); i++) {
				Team team = teams.get(i);
				if (team.containsPlayer(player)) {
					return team;
				}
			}
			return null;
		}
	}

	public Team findLowestTeam() {
		synchronized (LIST_LOCK) {
			Team lowestTeam = teams.get(0);
			for (int i = 0; i < teams.size(); i++) {
				Team team = teams.get(i);
				if (team.size() < lowestTeam.size()) {
					lowestTeam = team;
				}
			}
			return lowestTeam;
		}
	}
	
	public Team findTeamWithHighestScore() {
		synchronized (LIST_LOCK) {
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
	}

	public List<Team> findAllTeams() {
		synchronized (LIST_LOCK) {
			return new ArrayList<Team>(teams);
		}
	}
	
	public boolean equalScores() {
		synchronized (LIST_LOCK) {
			boolean equalScores = true;
			int score = teams.get(0).getScore();
			for (Team team : teams) {
				equalScores &= (score == team.getScore());
			}
			return equalScores;
		}
	}
	
	public boolean containsTeamWithName(String name) {
		return findTeamByName(name) != null;
	}

	public boolean containsTeamWithColor(TeamColor color) {
		return findTeamByColor(color) != null;
	}

	public int getMaximumAmountOfPlayers() {
		synchronized (LIST_LOCK) {
			int maximumAmount = 0;
			for (Team team : teams) {
				maximumAmount += team.getMaximumSize();
			}
			return maximumAmount;
		}
	}

	public int getNumberOfTeams() {
		synchronized (LIST_LOCK) {
			return teams.size();
		}
	}

}
