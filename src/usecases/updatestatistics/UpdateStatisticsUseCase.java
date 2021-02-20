package usecases.updatestatistics;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import entities.Statistics;
import entities.Team;
import entities.Teams;
import game.Game;
import gateways.GameGateway;
import gateways.StatisticsGateway;

public class UpdateStatisticsUseCase implements UpdateStatistics {

	private Game game;
	private HashMap<UUID, Statistics> statistics;
	private GameGateway gameGateway;
	private StatisticsGateway statisticsGateway;

	@Override
	public void execute(UpdateStatisticsRequest request) {
		String name = request.getGame();
		this.game = gameGateway.findGameByName(name);
		this.statistics = new HashMap<UUID, Statistics>();
		
		loadStatisticsOfPlayers();
		incrementGamesPlayedForAllPlayers();
		updateTotalTimePlayedForAllPlayers();
	
		if (isDraw()) {
			incrementGamesDraw();
		} else {
			incrementGamesWon();
			incrementGamesLost();
		}
		
		saveStatistics();
	}
	
	private boolean isDraw() {
		Teams teams = game.getTeams();
		return teams.equalScores();
	}
	
	private void updateTotalTimePlayedForAllPlayers() {
		int playingTimeInSeconds = game.getPlayingTimeInSeconds();
		for (UUID uuid : statistics.keySet()) {
			Statistics statistics = this.statistics.get(uuid);
			statistics.setTotalTimePlayedInSeconds(statistics.getTotalTimePlayedInSeconds() + playingTimeInSeconds);
		}
	}
	
	private void loadStatisticsOfPlayers() {
		Teams teams = game.getTeams();
		List<Team> teamsList = teams.findAllTeams();
		for (Team team : teamsList) {
			List<UUID> players = team.getPlayers();
			for (UUID player : players) {
				Statistics statistics = statisticsGateway.findStatistics(player);
				this.statistics.put(player, statistics);
			}
		}
	}
	
	private void incrementGamesWon() {
		Teams teams = game.getTeams();
		Team team = teams.findTeamWithHighestScore();
		for (UUID player : team.getPlayers()) {
			Statistics statistics = this.statistics.get(player);
			statistics.setGamesWon(statistics.getGamesWon() + 1);
		}
	}
	
	private void incrementGamesLost() {
		Teams teams = game.getTeams();
		Team team = teams.findTeamWithLowestScore();
		for (UUID player : team.getPlayers()) {
			Statistics statistics = this.statistics.get(player);
			statistics.setGamesLost(statistics.getGamesLost() + 1);
		}
	}
	
	private void incrementGamesDraw() {
		for (UUID uuid : statistics.keySet()) {
			Statistics statistics = this.statistics.get(uuid);
			statistics.setGamesDraw(statistics.getGamesDraw() + 1);
		}
	}

	private void incrementGamesPlayedForAllPlayers() {
		for (UUID uuid : statistics.keySet()) {
			Statistics statistics = this.statistics.get(uuid);
			statistics.setGamesPlayed(statistics.getGamesPlayed() + 1);
		}
	}
	
	private void saveStatistics() {
		for (UUID uuid : statistics.keySet()) {
			Statistics statistics = this.statistics.get(uuid);
				statisticsGateway.saveStatistics(uuid, statistics);
		}
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setStatisticsGateway(StatisticsGateway statisticsGateway) {
		this.statisticsGateway = statisticsGateway;
	}

}
