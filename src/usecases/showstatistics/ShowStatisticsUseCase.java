package usecases.showstatistics;

import entities.Statistics;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;
import gateways.StatisticsGateway;

public class ShowStatisticsUseCase implements ShowStatistics {

	private ShowStatisticsRequest request;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;
	private StatisticsGateway statisticsGateway;

	@Override
	public void execute(ShowStatisticsRequest request, ShowStatisticsResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		Statistics statistics = statisticsGateway.findStatistics(request.getPlayer());
		ShowStatisticsResponseModel responseModel = new ShowStatisticsResponseModel();
		responseModel.setGamesDraw(statistics.getGamesDraw());
		responseModel.setGamesLost(statistics.getGamesLost());
		responseModel.setGamesPlayed(statistics.getGamesPlayed());
		responseModel.setGamesWon(statistics.getGamesWon());
		responseModel.setPlayer(playerGateway.findPlayerNameById(request.getPlayer()));
		response.onPresentStatistics(responseModel);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.SHOW_STATS);
	}

	private void setRequest(ShowStatisticsRequest request) {
		this.request = request;
	}
	
	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

	@Override
	public void setStatisticsGateway(StatisticsGateway statisticsGateway) {
		this.statisticsGateway = statisticsGateway;
	}
	
}
