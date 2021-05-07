package gamestats;

import java.util.UUID;

import context.Context;
import game.Game;
import game.event.JoinListener;
import game.event.LeaveListener;
import game.event.TeamScoreListener;

public class GameStatisticsController implements JoinListener, LeaveListener, TeamScoreListener {

	public GameStatisticsController(Game game) {
		game.addJoinListener(this);
		game.addLeaveListener(this);
		game.addTeamScoreListener(this);
	}
	
	@Override
	public void onPlayerLeave(Game game, UUID player) {
		Context.gameStatisticGateway.removeStatisticsOfPlayer(player);
	}

	@Override
	public void onPlayerJoin(Game game, UUID player) {
		Context.gameStatisticGateway.createStatisticsForPlayer(game.getName(), player);
	}
	
	@Override
	public void onTeamScored(String gameName, String team) {
		UUID uniquePlayerId = Context.gameStatisticGateway.findLastHitter(gameName);
		GameStatistic gameStatistic = Context.gameStatisticGateway.findByPlayerId(uniquePlayerId);
		gameStatistic.setScoredGoals(gameStatistic.getScoredGoals() + 1);
	}

}
