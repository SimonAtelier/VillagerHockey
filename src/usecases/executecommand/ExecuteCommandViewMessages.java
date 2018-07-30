package usecases.executecommand;

public interface ExecuteCommandViewMessages {

	static final String EXECUTE_COMMAND_UNKNOWN_COMMAND = "Command konnte nicht ausgeführt werden. Ein Command mit dem Namen '$name$' ist nicht bekannt.";

	static final String EXECUTE_COMMAND_TOO_MANY_ARGUMENTS = "Command '$name$' konnte nicht ausgeführt werden. Es wurden zu viele Parameter angegeben. Syntax: $syntax$";

	static final String EXECUTE_COMMAND_MISSING_ARGUMENTS = "Command '$name$' konnte nicht ausgeführt werden. Es fehlen Parameter. Syntax: $syntax$";
	
	static final String EXECUTE_COMMAND_CANNOT_EXECUTE_WHILE_INGAME = "Du kannst dieses Command nicht ausführen während du im laufenden Spiel bist";
	
}
