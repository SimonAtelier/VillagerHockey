package usecases.encaps.showstatistics;

import gamestats.GameStatistic;
import gamestats.GameStatisticGateway;
import gamestats.StatisticKeys;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class ShowStatisticsUseCase implements ShowStatistics {

	private GameStatistic gameStatistic;
	private ShowStatisticsRequest request;
	private ShowStatisticsResponse response;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;
	private GameStatisticGateway gameStatisticGateway;

	@Override
	public void execute(ShowStatisticsRequest request, ShowStatisticsResponse response) {
		setRequest(request);
		setResponse(response);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		loadStatistics();
		sendResponse();
	}
	
	private void loadStatistics() {
		getGameStatisticGateway().createStatisticsForPlayer(getRequest().getPlayer());
		gameStatistic = findGameStatistic();
	}
	
	private void sendResponse() {
		getResponse().onPresentStatistics(createResponseModel());
	}
	
	private ShowStatisticsResponseModel createResponseModel() {
		ShowStatisticsResponseModel responseModel = new ShowStatisticsResponseModel();
		responseModel.setGamesDraw(getStatisticValue(StatisticKeys.GAMES_DRAW));
		responseModel.setGamesLost(getStatisticValue(StatisticKeys.GAMES_LOST));
		responseModel.setGamesPlayed(getStatisticValue(StatisticKeys.GAMES_PLAYED));
		responseModel.setGamesWon(getStatisticValue(StatisticKeys.GAMES_WON));
		responseModel.setPlayer(findPlayerName());
		return responseModel;
	}
	
	private int getStatisticValue(String key) {
		return gameStatistic.getValue(key);
	}
	
	private GameStatistic findGameStatistic() {
		return getGameStatisticGateway().findByPlayerId(getRequest().getPlayer());
	}
	
	private String findPlayerName() {
		return getPlayerGateway().findPlayerNameById(getRequest().getPlayer());
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(getRequest().getPlayer(), Permissions.SHOW_STATS);
	}
	
	private ShowStatisticsRequest getRequest() {
		return request;
	}

	private void setRequest(ShowStatisticsRequest request) {
		this.request = request;
	}
	
	private ShowStatisticsResponse getResponse() {
		return response;
	}
	
	private void setResponse(ShowStatisticsResponse response) {
		this.response = response;
	}
	
	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
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
	public void setGameStaticticGateway(GameStatisticGateway gameStatisticGateway) {
		this.gameStatisticGateway = gameStatisticGateway;
	}
	
}
