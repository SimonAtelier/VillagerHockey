package usecases.executecommand;

public interface ExecuteCommandView {
	
	void displayToManyArguments(String name, String syntax);
	
	void displayMissingArguments(String name, String syntax);
	
	void displayUnknownCommand(String name);

}
