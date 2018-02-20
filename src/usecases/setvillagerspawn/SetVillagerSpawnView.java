package usecases.setvillagerspawn;

public interface SetVillagerSpawnView {

	void displayNoPermission();
	
	void displayNoSuchGame();
	
	void displayVillagerSpawnSuccessfullySet(String game);
	
}
