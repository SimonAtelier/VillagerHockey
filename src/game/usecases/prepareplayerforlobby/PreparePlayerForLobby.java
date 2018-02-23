package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import config.Configuration;
import gateways.PermissionGateway;

public interface PreparePlayerForLobby {

	void execute(UUID uniquePlayerId, PreparePlayerForLobbyResponse response);
	
	void setConfiguration(Configuration configuration);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface PreparePlayerForLobbyResponse {
		
		void present(PreparePlayerForLobbyResponseModel responseModel);
		
	}
	
}
