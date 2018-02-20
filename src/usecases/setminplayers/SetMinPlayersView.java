package usecases.setminplayers;

public interface SetMinPlayersView {

	void displayNoPermission();
	
	void displayNoSuchGame(String game);
	
	void displayMinPlayersIsNotAValidNumber(String minPlayers);
	
	void displayMinPlayersSuccessfullySet(String game, String minPlayers);
	
}
