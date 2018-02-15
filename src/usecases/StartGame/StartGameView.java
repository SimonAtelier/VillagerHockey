package usecases.StartGame;

public interface StartGameView {

	void displayNoPermission();
	
	void displayNoSuchGame(String game);
	
	void displayAlreadyStarted(String game);
	
	void displaySuccessfullyStarted(String game);
	
}
