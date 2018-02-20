package usecases.executecommand;

import java.util.List;
import java.util.UUID;

import entities.command.Command;
import gateways.CommandGateway;

public class ExecuteCommandUseCase implements ExecuteCommand, RootCommandLabel {

	private CommandGateway commandGateway;
	
	@Override
	public void execute(UUID player, String name, List<String> arguments, ExecuteCommandResponse response) {
		Command command = commandGateway.findCommandByName(name);
		
		if (!commandGateway.containsCommandWithName(name)) {
			response.onUnknownCommand(name);
			return;
		}
		
		if (command.getArgumentLabels().length < arguments.size()) {
			response.onToManyArguments(name, createSyntax(command));
			return;
		}
		
		if (command.getArgumentLabels().length > arguments.size()) {
			response.onMissingArguments(name, createSyntax(command));
			return;
		}
		
		command.execute(player, arguments);
	}
	
	private String createSyntax(Command command) {
		return "/" + ROOT_COMMAND_LABEL + " " + command.getSyntax();
	}

	@Override
	public void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
}
