package gateways;

import java.util.List;

import command.Command;
import command.CommandRegistration;

public interface CommandGateway extends CommandRegistration {

	Command findCommandByName(String name);
	
	List<Command> findAllCommands();
		
	boolean containsCommandWithName(String name);
	
}
