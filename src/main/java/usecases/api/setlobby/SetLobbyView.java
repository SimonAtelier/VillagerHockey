package usecases.api.setlobby;

public interface SetLobbyView {

	void displayNoPermission();
	
	void displayNoGameWithSuchName(String game);
	
	void displayLobbySuccessfullySet(String game);
	
}
