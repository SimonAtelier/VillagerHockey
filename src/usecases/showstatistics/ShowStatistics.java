package usecases.showstatistics;

import java.util.UUID;

import gateways.PermissionGateway;
import gateways.PlayerGateway;
import gateways.StatisticsGateway;

public interface ShowStatistics {

	void execute(ShowStatisticsRequest request, ShowStatisticsResponse response);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	void setStatisticsGateway(StatisticsGateway statisticsGateway);
	
	public interface ShowStatisticsRequest {
		
		UUID getPlayer();
		
	}
	
	public interface ShowStatisticsResponse {
		
		void onNoPermission();
		
		void onPresentStatistics(ShowStatisticsResponseModel statistics);
		
	}
	
}
