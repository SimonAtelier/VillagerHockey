package usecases.prepareplayerforlobby;

public interface LobbyView {

	void displaySelectTeam();
	
	void displayForceStart();
	
	void displayLeaveGame();
	
	void displayAchievements();
	
	void displayGameMode(String gameMode);
	
	void displayFoodLevel(int foodLevel);
	
	void displayLevel(int level);
	
	void displayMaxHealth();
	
	void displayClearAllPotionEffects();
	
	void displayExperience(int experience);
	
}
