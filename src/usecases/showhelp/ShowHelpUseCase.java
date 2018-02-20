package usecases.showhelp;

import java.util.List;
import java.util.UUID;

import entities.command.Command;
import gateways.CommandGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class ShowHelpUseCase implements ShowHelp {

	private UUID player;
	private CommandGateway commandGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(UUID player, ShowHelpResponse response) {
		setPlayer(player);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		List<Command> commands = commandGateway.findAllCommands();
		response.presentHelp(commands);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.SHOW_HELP);
	}
	
	private void setPlayer(UUID player) {
		this.player = player;
	}

	@Override
	public void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

}
