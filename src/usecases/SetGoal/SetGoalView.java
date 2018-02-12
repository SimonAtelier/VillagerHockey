package usecases.SetGoal;

public interface SetGoalView {

	void displayNoSuchGame(String name);
	
	void displayNoSuchTeam(String name);
	
	void displayInvalidLocationId(String id);

	void displayNoPermission();
	
	void displayGoalLocationSuccessfullySet(String locationId);
	
	void displayNeedToSetLocationWithIdFirst(String id);
	
}
