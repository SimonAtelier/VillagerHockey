package gateways;

import java.util.List;

public interface Configuration {

	String getPrefix();
	
	int getLobbyTime();
	
	int getLobbyGameMode();
	
	boolean isMapTitleEnabled();
	
	boolean isGoalTitleEnabled();
	
	String getGameListTitle();
	
	String getVillagerName();
	
	boolean isVillagerAIEnabled();
	
	boolean isUseRandomVillagerNamesEnabled();
	
	public List<String> getRandomVillagerNames();
	
}
