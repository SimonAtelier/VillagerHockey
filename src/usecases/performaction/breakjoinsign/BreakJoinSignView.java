package usecases.performaction.breakjoinsign;

public interface BreakJoinSignView {

	void displayNoPermission();

	void displayNoSuchGame(String game);
	
	void displaySuccessfullyRemove(String game);
	
	void discardBreaking();
	
}
