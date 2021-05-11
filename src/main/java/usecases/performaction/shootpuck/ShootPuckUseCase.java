package usecases.performaction.shootpuck;

import java.util.UUID;

import game.Game;
import gamestats.GameStatistic;
import gamestats.GameStatisticGateway;
import gamestats.StatisticKeys;
import gateways.GameGateway;

public class ShootPuckUseCase implements ShootPuck {
	
	private ShootPuckRequest request;
	private ShootPuckResponse response;
	private GameStatisticGateway gameStatisticGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(ShootPuckRequest request, ShootPuckResponse response) {
		setRequest(request);
		setResponse(response);
		
		updateLastHitter();
		updatePuckHits();
		response.presentShootPuck();
	}
	
	private void updateLastHitter() {
		UUID lastHitter = getRequest().getPlayer();
		Game game = getGameGateway().findGameOfPlayer(lastHitter);
		getGameStatisticsGateway().updateLastHitter(game.getName(), lastHitter);
	}
	
	private void updatePuckHits() {
		GameStatistic gameStatistic = getGameStatisticsGateway().findByPlayerId(getRequest().getPlayer());
		gameStatistic.add(StatisticKeys.PUCK_HITS_CURRENT_GAME, 1);
		gameStatistic.add(StatisticKeys.PUCK_HITS_TOTAL, 1);
	}
	
	private ShootPuckRequest getRequest() {
		return request;
	}

	private void setRequest(ShootPuckRequest request) {
		this.request = request;
	}

	private ShootPuckResponse getResponse() {
		return response;
	}

	private void setResponse(ShootPuckResponse response) {
		this.response = response;
	}
	
	private GameStatisticGateway getGameStatisticsGateway() {
		return gameStatisticGateway;
	}

	@Override
	public void setGameStatisticsGateway(GameStatisticGateway gameStatisticGateway) {
		this.gameStatisticGateway = gameStatisticGateway;
	}
	
	private GameGateway getGameGateway() {
		return gameGateway;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
