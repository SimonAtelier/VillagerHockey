package usecases.hockey.updatestatistics;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import entities.Team;
import entities.Teams;
import game.Game;
import gamestats.GameStatistic;
import gamestats.GameStatisticGateway;
import gamestats.StatisticKeys;
import gateways.GameGateway;

public class UpdateStatisticsUseCase implements UpdateStatistics {

	private HashSet<UUID> players;
	private Game game;
	private GameGateway gameGateway;
	private GameStatisticGateway gameStatisticGateway;

	@Override
	public void execute(UpdateStatisticsRequest request) {
		String name = request.getGame();
		this.game = gameGateway.findGameByName(name);

		players = new HashSet<UUID>(game.getUniquePlayerIds());

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
		for (UUID uuid : getPlayers()) {
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
		for (UUID uuid : getPlayers()) {
			gameStatisticGateway.findByPlayerId(uuid).add(StatisticKeys.TOTAL_TIME_PLAYED_IN_SECONDS,
					playingTimeInSeconds);
		}
	}

	private void incrementGamesWon() {
		Teams teams = game.getTeams();
		Team team = teams.findTeamWithHighestScore();
		for (UUID player : team.getPlayers()) {
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_WON, 1);
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.WINNING_STREAK, 1);
			updateLongestWinningStreak(player);
		}
	}

	private void incrementGamesLost() {
		Teams teams = game.getTeams();
		Team team = teams.findTeamWithLowestScore();
		for (UUID player : team.getPlayers()) {
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_LOST, 1);
			updateLastWinningStreak(player);
			updateLongestWinningStreak(player);
			gameStatisticGateway.findByPlayerId(player).setValue(StatisticKeys.WINNING_STREAK, 0);
		}
	}

	private void incrementGamesDraw() {
		for (UUID player : getPlayers()) {
			gameStatisticGateway.findByPlayerId(player).add(StatisticKeys.GAMES_DRAW, 1);
			updateLastWinningStreak(player);
			updateLongestWinningStreak(player);
			gameStatisticGateway.findByPlayerId(player).setValue(StatisticKeys.WINNING_STREAK, 0);
		}
	}

	private void incrementGamesPlayedForAllPlayers() {
		for (UUID uuid : getPlayers()) {
			gameStatisticGateway.findByPlayerId(uuid).add(StatisticKeys.GAMES_PLAYED, 1);
		}
	}

	private void saveStatistics() {
		for (UUID uuid : getPlayers()) {
			gameStatisticGateway.saveStatistics(uuid);
		}
	}

	private Collection<UUID> getPlayers() {
		return players;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setGameStatisticGateway(GameStatisticGateway gameStatisticGateway) {
		this.gameStatisticGateway = gameStatisticGateway;
	}

}
