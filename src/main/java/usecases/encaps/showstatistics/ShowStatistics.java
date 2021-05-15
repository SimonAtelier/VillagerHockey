package usecases.encaps.showstatistics;

import java.util.UUID;

import gamestats.GameStatisticGateway;
import gateways.PermissionGateway;
import gateways.PlayerGateway;

public interface ShowStatistics {

	void execute(ShowStatisticsRequest request, ShowStatisticsResponse response);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
		
	void setGameStaticticGateway(GameStatisticGateway gameStatisticGateway);
	
	public interface ShowStatisticsRequest {
		
		UUID getPlayer();
		
	}
	
	public interface ShowStatisticsResponse {
		
		void onNoPermission();
		
		void onPresentStatistics(ShowStatisticsResponseModel statistics);
		
	}
	
}
