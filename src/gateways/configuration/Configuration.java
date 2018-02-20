package gateways.configuration;

import java.util.List;

public interface Configuration extends WinTitleConfiguration, MapTitleConfiguration {

	String getPrefix();
	
	int getLobbyTime();
	
	int getLobbyGameMode();
	
	boolean isGoalTitleEnabled();
	
	String getGameListTitle();
	
	String getVillagerName();
	
	boolean isVillagerAIEnabled();
	
	boolean isUseRandomVillagerNamesEnabled();
	
	public List<String> getRandomVillagerNames();
		
}
