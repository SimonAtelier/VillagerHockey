package usecases.PerformAction.BreakJoinSign;

public interface BreakJoinSignView {

	void displayNoPermission();

	void displayNoSuchGame(String game);
	
	void displaySuccessfullyRemove(String game);
	
	void discardBreaking();
	
}
