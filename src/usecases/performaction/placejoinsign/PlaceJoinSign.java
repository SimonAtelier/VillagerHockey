package usecases.performaction.placejoinsign;

import java.util.UUID;

import gateways.GameGateway;
import gateways.JoinSignGateway;
import gateways.PermissionGateway;

public interface PlaceJoinSign {

	void execute(PlaceJoinSignRequest request, PlaceJoinSignResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setJoinSignGateway(JoinSignGateway joinSignGateway);
	
	public interface PlaceJoinSignRequest {
		
		double getX();
		
		double getY();

		double getZ();
		
		String getWorld();
		
		UUID getPlayer();
		
		String getGame();
		
	}
	
	public interface PlaceJoinSignResponse {
		
		void onNoPermission();
		
		void onNoSuchGame(String game);
		
		void onJoinSignSuccessfullySet(String game, ResponseModel responseModel);
		
	}
	
}
