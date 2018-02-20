package gateways;

import java.util.List;

import entities.command.Command;
import entities.command.CommandRegistration;

public interface CommandGateway extends CommandRegistration {

	Command findCommandByName(String name);
	
	List<Command> findAllCommands();
		
	boolean containsCommandWithName(String name);
	
}
