package gateways;

import java.util.List;

import entities.Command.Command;
import entities.Command.CommandRegistration;

public interface CommandGateway extends CommandRegistration {

	Command findCommandByName(String name);
	
	List<Command> findAllCommands();
		
	boolean containsCommandWithName(String name);
	
}
