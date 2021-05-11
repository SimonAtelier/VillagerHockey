package entities.command;

import java.util.List;
import java.util.UUID;

public interface Command {

	void execute(UUID player, List<String> arguments);
	
	String getName();
	
	String[] getArgumentLabels();
	
	String getSyntax();
	
 }
