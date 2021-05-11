package usecases.api.executenonegamecommand;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;

public interface ExecuteNoneGameCommand {

	void execute(UUID uniquePlayerId, String rawCommandMessage, ExecuteNoneGameCommandResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface ExecuteNoneGameCommandResponse {
		
		void onNoPermission();
		
	}
	
}
