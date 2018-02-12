package usecases.PerformAction.ShootPuck;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PlayerGateway;

public interface ShootPuck {

	void execute(ShootPuckRequest request, ShootPuckResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPlayerGateway(PlayerGateway playerGateway);
	
	public interface ShootPuckRequest {
		
		UUID getPlayer();
		
	}
	
	public interface ShootPuckResponse {
		
		void presentShootPuck(UUID player);
		
	}
	
}
