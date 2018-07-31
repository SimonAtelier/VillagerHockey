package usecases.executecommand;

public interface ExecuteCommandViewMessages {

	static final String EXECUTE_COMMAND_UNKNOWN_COMMAND = "Command konnte nicht ausgef�hrt werden. Ein Command mit dem Namen '$name$' ist nicht bekannt.";

	static final String EXECUTE_COMMAND_TOO_MANY_ARGUMENTS = "Command '$name$' konnte nicht ausgef�hrt werden. Es wurden zu viele Parameter angegeben. Syntax: $syntax$";

	static final String EXECUTE_COMMAND_MISSING_ARGUMENTS = "Command '$name$' konnte nicht ausgef�hrt werden. Es fehlen Parameter. Syntax: $syntax$";
	
}
