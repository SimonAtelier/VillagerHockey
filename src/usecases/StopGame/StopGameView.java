package usecases.StopGame;

public interface StopGameView {

	void displayNoPermission();
	
	void displayNoSuchGame(String game);
	
	void displayAlreadyStopped(String game);
	
	void displaySuccessfullyStopped(String game);
	
}
