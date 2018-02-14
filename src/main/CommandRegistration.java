package main;

import gateways.CommandGateway;

public interface CommandRegistration {

	void registerCommands(CommandGateway commandGateway);
	
}
