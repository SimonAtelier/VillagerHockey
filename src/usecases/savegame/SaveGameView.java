package usecases.savegame;

public interface SaveGameView {

	void displayNoSuchGame(String name);
	
	void displayGameSuccessfullySaved();
	
	void displayNoPermission();
	
	void displayCannotSaveGameNoLobbySet();
	
	void displayCannotSaveGameNoVillagerSpawnSet();
	
	void displayCannotSavePlayingMustBeGreaterThanZero();
	
	void displayCannotSaveNumberOfTeamsIsNotTwo();
	
	void displayCannotSaveNotAllGoalsSet();
	
	void displayCannotSaveSpawnLocationsMissing();
	
	void displayCannotSaveAmountOfTeamSpawnsIsNotEqual();
	
}
