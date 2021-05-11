package usecases.hockey.updatestatistics;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import entities.Statistics;
import entities.Team;
import entities.Teams;
import game.Game;
import gamestats.GameStatistic;
import gamestats.GameStatisticGateway;
import gamestats.GameStatsYaml;
import gamestats.StatisticKeys;
import gateways.GameGateway;
import gateways.StatisticsGateway;

public class UpdateStatisticsUseCase implements UpdateStatistics {

	private Game game;
	private HashMap<UUID, Statistics> statistics;
	private GameGateway gameGateway;
	private StatisticsGateway statisticsGateway;
	private GameStatisticGateway gameStatisticGateway;

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

		if (isToZero()) {
			updateToZero();
		}

		saveStatistics();

		resetPuckHits();
		GameStatsYaml save = new GameStatsYaml();
		for (UUID uniquePlayerId : game.getUniquePlayerIds()) {
			save.save(uniquePlayerId);
		}
	}

	private void updateLongestWinningStreak(UUID player) {
		GameStatistic gameStatistic = gameStatisticGateway.findByPlayerId(player);
		int currentStreak = gameStatistic.getValue(StatisticKeys.WINNING_STREAK);
		int longestStreak = gameStatistic.getValue(StatisticKeys.WINNING_STREAK_LONGEST);
		if (currentStreak > longestStreak) {
			gameStatistic.setValue(StatisticKeys.WINNING_STREAK_LONGEST, currentStreak);
		}
	}

	private void updateLastWinningStreak(UUID player) {
		gameStatisticGateway.findByPlayerId(player).setValue(StatisticKeys.WINNING_STREAK_LAST,
				gameStatisticGateway.findByPlayerId(player).getValue(StatisticKeys.WINNING_STREAK));
	}

	private void resetPuckHits() {
		for (UUID uuid : statistics.keySet()) {
			gameStatisticGateway.findByPlayerId(uuid).setValue(StatisticKeys.PUCK_HITS_CURRENT_GAME, 0);
		}
	}

	private void updateToZero() {
		Teams teams = game.getTeams();
		Team team = teams.findTeamWithHighestScore();
		for (UUID player : team.getPlayers()) {
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_WON_TO_ZERO, 1);
		}
	}

	private boolean isDraw() {
		Teams teams = game.getTeams();
		return teams.equalScores();
	}

	private boolean isToZero() {
		int zeroCount = 0;
		Teams teams = game.getTeams();
		for (Team team : teams.findAllTeams()) {
			if (team.getScore() == 0)
				zeroCount++;
		}
		return zeroCount == teams.getNumberOfTeams() - 1;
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
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_WON, 1);
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.WINNING_STREAK, 1);
			updateLongestWinningStreak(player);
		}
	}

	private void incrementGamesLost() {
		Teams teams = game.getTeams();
		Team team = teams.findTeamWithLowestScore();
		for (UUID player : team.getPlayers()) {
			Statistics statistics = this.statistics.get(player);
			statistics.setGamesLost(statistics.getGamesLost() + 1);
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_LOST, 1);
			updateLastWinningStreak(player);
			updateLongestWinningStreak(player);
			gameStatisticGateway.findByPlayerId(player).setValue(StatisticKeys.WINNING_STREAK, 0);
		}
	}

	private void incrementGamesDraw() {
		for (UUID player : statistics.keySet()) {
			Statistics statistics = this.statistics.get(player);
			statistics.setGamesDraw(statistics.getGamesDraw() + 1);
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_DRAW, 1);
			updateLastWinningStreak(player);
			updateLongestWinningStreak(player);
			gameStatisticGateway.findByPlayerId(player).setValue(StatisticKeys.WINNING_STREAK, 0);
		}
	}

	private void incrementGamesPlayedForAllPlayers() {
		for (UUID uuid : statistics.keySet()) {
			Statistics statistics = this.statistics.get(uuid);
			statistics.setGamesPlayed(statistics.getGamesPlayed() + 1);
			gameStatisticGateway.findByPlayerId(uuid).add(StatisticKeys.GAMES_PLAYED, 1);
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

	private GameStatisticGateway getGameStatisticGateway() {
		return gameStatisticGateway;
	}

	@Override
	public void setGameStatisticGateway(GameStatisticGateway gameStatisticGateway) {
		this.gameStatisticGateway = gameStatisticGateway;
	}

}
