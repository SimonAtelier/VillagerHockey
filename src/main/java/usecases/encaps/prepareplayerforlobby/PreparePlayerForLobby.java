package usecases.encaps.prepareplayerforlobby;

import java.util.UUID;

import entities.config.Configuration;
import gateways.PermissionGateway;
import gateways.PlayerDataGateway;

public interface PreparePlayerForLobby {

	void execute(UUID uniquePlayerId, PreparePlayerForLobbyResponse response);
	
	void setConfiguration(Configuration configuration);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	void setPlayerDataGateway(PlayerDataGateway playerDataGateway);
	
	public interface PreparePlayerForLobbyResponse {
		
		void present(PreparePlayerForLobbyResponseModel responseModel);
		
	}
	
}
