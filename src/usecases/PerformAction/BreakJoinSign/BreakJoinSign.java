package usecases.PerformAction.BreakJoinSign;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface BreakJoinSign {

	void execute(BreakJoinSignRequest request, BreakJoinSignResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface BreakJoinSignRequest {
		
		double getX();
		
		double getY();
		
		double getZ();
		
		String getWorld();
				
		String getFirstLine();
		
		String getSecondLine();
		
		String getThirdLine();
		
		String getFourthLine();
		
		UUID getPlayer();
		
	}
	
	public interface BreakJoinSignResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onSuccessfullyRemoved(String game);
		
		void onDiscardBreaking();
		
	}
	
}
