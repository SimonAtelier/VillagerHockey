package usecases.AddTeamSpawn;

public interface AddTeamSpawnView {

	void displayNoSuchGame();
	
	void displayNoSuchTeam();
	
	void displayTeamSpawnSuccessfullyAdd(String team);
	
	void displayNoPermission();
	
}
