package usecases.api.executenonegamecommand;

import java.util.UUID;

import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import usecases.api.executecommand.RootCommandLabel;

public class ExecuteNoneGameCommandUseCase implements ExecuteNoneGameCommand {
	
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(UUID uniquePlayerId, String rawCommandMessage, ExecuteNoneGameCommandResponse response) {
		if (!gameGateway.isIngame(uniquePlayerId)) {
			return;
		}
			
		if (isGameCommand(rawCommandMessage)) {
			return;
		}
			
		if (!permissionGateway.hasPermission(uniquePlayerId, Permissions.EXECUTE_NONE_GAME_COMMANDS)) {
			response.onNoPermission();
			return;
		}
	}
	
	private boolean isGameCommand(String rawCommandMessage) {
		return rawCommandMessage.startsWith("/" + RootCommandLabel.ROOT_COMMAND_LABEL + " ");
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}
	
}
