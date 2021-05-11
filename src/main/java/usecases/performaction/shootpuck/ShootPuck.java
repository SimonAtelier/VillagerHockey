package usecases.performaction.shootpuck;

import java.util.UUID;

import gamestats.GameStatisticGateway;
import gateways.GameGateway;

public interface ShootPuck {

	void execute(ShootPuckRequest request, ShootPuckResponse response);
	
	void setGameStatisticsGateway(GameStatisticGateway gameStatisticGateway);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface ShootPuckRequest {
		
		UUID getPlayer();
		String getPuckName();
		
	}
	
	public interface ShootPuckResponse {
		
		void presentShootPuck();
		
	}
	
}
