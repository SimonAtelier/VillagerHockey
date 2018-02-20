package usecases.executecommand;

import java.util.List;
import java.util.UUID;

import gateways.CommandGateway;

public interface ExecuteCommand {
	
	void execute(UUID player, String name, List<String> arguments, ExecuteCommandResponse response);

	void setCommandGateway(CommandGateway commandGateway);
	
	public interface ExecuteCommandResponse {
		
		void onToManyArguments(String name, String syntax);
		
		void onMissingArguments(String name, String syntax);
		
		void onUnknownCommand(String name);
		
	}
	
}
