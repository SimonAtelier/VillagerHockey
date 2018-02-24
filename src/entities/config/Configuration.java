package entities.config;

import java.util.List;

public interface Configuration extends WinTitleConfiguration, MapTitleConfiguration, GoalTitleConfiguration {

	String getPrefix();
	
	String getChatWithAllLabel();
	
	int getLobbyTime();
	
	String getLobbyGameMode();
	
	public boolean isAutobalanceEnabled();
	
	public boolean isAchievementsEnabled();
	
	String getVillagerName();
	
	boolean isVillagerAIEnabled();
	
	boolean isUseRandomVillagerNamesEnabled();
	
	public List<String> getRandomVillagerNames();
		
}
