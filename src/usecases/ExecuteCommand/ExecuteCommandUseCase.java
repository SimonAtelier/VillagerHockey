package usecases.ExecuteCommand;

import java.util.List;
import java.util.UUID;

import gateways.CommandGateway;
import spigot.Command;

public class ExecuteCommandUseCase implements ExecuteCommand {

	private CommandGateway commandGateway;
	
	@Override
	public void execute(UUID player, String name, List<String> arguments, ExecuteCommandResponse response) {
		Command command = commandGateway.findCommandByName(name);
		
		if (!commandGateway.containsCommandWithName(name)) {
			response.onUnknownCommand(name);
			return;
		}
		
		if (command.getArgumentLabels().length < arguments.size()) {
			response.onToManyArguments(name, command.getSyntax());
			return;
		}
		
		if (command.getArgumentLabels().length > arguments.size()) {
			response.onMissingArguments(name, command.getSyntax());
			return;
		}
		
		command.execute(player, arguments);
	}

	@Override
	public void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
}
