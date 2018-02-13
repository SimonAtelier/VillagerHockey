package usecases.SaveGame;

public interface SaveGameView {

	void displayNoSuchGame(String name);
	
	void displayGameSuccessfullySaved();
	
	void displayNoPermission();
	
	void displayError();
	
	void displayCannotSaveGameNoLobbySet();
	
	void displayCannotSaveGameNoVillagerSpawnSet();
	
	void displayCannotSavePlayingMustBeGreaterThanZero();
	
	void displayCannotSaveNumberOfTeamsIsNotTwo();
	
}
