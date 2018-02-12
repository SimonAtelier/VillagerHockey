package usecases.ShowHelp;

import java.util.List;
import java.util.UUID;

import command.Command;
import gateways.CommandGateway;
import gateways.PermissionGateway;

public interface ShowHelp {

	void execute(UUID player, ShowHelpResponse response);
	
	void setCommandGateway(CommandGateway commandGateway);
	
	void setPermissionGateway(PermissionGateway permissionGateway);
	
	public interface ShowHelpResponse {
		
		void onNoPermission();
		
		void presentHelp(List<Command> commands);
		
	}
	
}
