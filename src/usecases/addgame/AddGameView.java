package usecases.addgame;

public interface AddGameView {
	
	void displayGameWithNameAlreadyExists(String name);
	
	void displayGameSuccessfullyAdded(String game);
	
	void displayInvalidName();
	
	void displayNoPermission();
	
}
