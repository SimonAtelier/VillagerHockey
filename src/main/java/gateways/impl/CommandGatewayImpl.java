package gateways.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.command.Command;
import gateways.CommandGateway;

public class CommandGatewayImpl implements CommandGateway {
	
	private HashMap<String, Command> commands;
	
	public CommandGatewayImpl() {
		commands = new HashMap<String, Command>();
	}

	@Override
	public Command findCommandByName(String name) {
		return commands.get(name);
	}
	
	@Override
	public List<Command> findAllCommands() {
		return new ArrayList<Command>(commands.values());
	}

	@Override
	public void registerCommand(Command command) {
		String commandName = command.getName();
		if (commandName == null)
			throw new NullPointerException("Command name cannot be null.");
		commands.put(command.getName(), command);
	}

	@Override
	public boolean containsCommandWithName(String name) {
		return commands.containsKey(name);
	}

}
