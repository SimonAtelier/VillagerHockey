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
	
	public boolean isWinTitleEnabled();
	
	public int getWinTitleFadeInTimeInSeconds();
	
	public int getWinTitleFadeOutTimeInSeconds();
	
	public int getWinTitleStayTimeInSeconds();
	
	public int getWinSubtitleFadeInTimeInSeconds();
	
	public int getWinSubtitleFadeOutTimeInSeconds();
	
	public int getWinSubtitleStayTimeInSeconds();
	
}
