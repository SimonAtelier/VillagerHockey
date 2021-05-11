package usecases.hockey.destroypinata;

import java.util.List;
import java.util.UUID;

import game.Game;
import gamestats.GameStatistic;
import gamestats.GameStatisticGateway;
import gamestats.StatisticKeys;
import gateways.GameGateway;
import gateways.PlayerGateway;

public class DestroyPinataUseCase implements DestroyPinata {

	private int points = 2;
	private Game game;
	private DestroyPinataRequest request;
	private DestroyPinataResponse response;
	private GameGateway gameGateway;
	private PlayerGateway playerGateway;
	private GameStatisticGateway gameStatisticGateway;

	@Override
	public void execute(DestroyPinataRequest request, DestroyPinataResponse response) {
		setRequest(request);
		setResponse(response);

		if (!findGame())
			return;

		removeVillager();
		updateStatistic();
		score();
		sendDestroyedPinataResponse();
	}
	
	private void updateStatistic() {
		GameStatistic gameStatistic = getGameStatisticGateway().findByPlayerId(getRequest().getDestroyer());
		gameStatistic.add(StatisticKeys.PINATAS_SMASHED, 1);
	}

	private void removeVillager() {
		game.getVillagerSpawner().removeVillager();
	}

	private void score() {
		game.onTeamScored(game.getTeams().findTeamOfPlayer(getRequest().getDestroyer()).getName(), points);
	}

	private boolean findGame() {
		game = getGameGateway().findGameOfPlayer(getRequest().getDestroyer());
		return game != null;
	}

	private DestroyPinataResponseModel createResponseModel() {
		DestroyPinataResponseModel responseModel = new DestroyPinataResponseModel();
		responseModel.setDestroyerName(getDestroyerName());
		responseModel.setPlayers(getPlayers());
		responseModel.setDestroyerTeamName(getDestroyerTeamName());
		responseModel.setPoints(points);
		return responseModel;
	}

	private String getDestroyerName() {
		return getPlayerGateway().findPlayerNameById(getRequest().getDestroyer());
	}

	private String getDestroyerTeamName() {
		return game.getTeams().findTeamOfPlayer(getRequest().getDestroyer()).getName();
	}

	private List<UUID> getPlayers() {
		return game.getUniquePlayerIds();
	}

	private void sendDestroyedPinataResponse() {
		getResponse().onDestroyedPinata(createResponseModel());
	}

	private DestroyPinataRequest getRequest() {
		return request;
	}

	private void setRequest(DestroyPinataRequest request) {
		this.request = request;
	}

	private DestroyPinataResponse getResponse() {
		return response;
	}

	private void setResponse(DestroyPinataResponse response) {
		this.response = response;
	}

	private GameGateway getGameGateway() {
		return gameGateway;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	private PlayerGateway getPlayerGateway() {
		return playerGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

	private GameStatisticGateway getGameStatisticGateway() {
		return gameStatisticGateway;
	}

	@Override
	public void setGameStatisticGateway(GameStatisticGateway gameStatisticGateway) {
		this.gameStatisticGateway = gameStatisticGateway;
	}

}
