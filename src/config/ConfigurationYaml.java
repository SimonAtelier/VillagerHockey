package config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationYaml implements Configuration {

	private YamlConfiguration config;

	public ConfigurationYaml(YamlConfiguration config) {
		this.config = config;
	}
	
	@Override
	public String getPrefix() {
		if (config.contains("chat-prefix")) {
			return config.getString("chat-prefix");
		}
		return "[VillagerHockey]";
	}

	@Override
	public int getLobbyTime() {
		if (config.contains("lobby-time"))
			return config.getInt("lobby-time");
		return 5;
	}

	@Override
	public String getLobbyGameMode() {
		if (config.contains("lobby-gamemode"))
			return config.getString("lobby-gamemode");
		return "ADVENTURE";
	}
	
	@Override
	public boolean isAutobalanceEnabled() {
		if (config.contains("auto-balance-enabled"))
			return config.getBoolean("auto-balance-enabled");
		return false;
	}

	@Override
	public String getVillagerName() {
		if (config.contains("villager-default-name"))
			return config.getString("villager-default-name");
		return "";
	}

	@Override
	public boolean isVillagerAIEnabled() {
		if (config.contains("villager-ai-enabled"))
			return config.getBoolean("villager-ai-enabled");
		return false;
	}

	@Override
	public boolean isUseRandomVillagerNamesEnabled() {
		if (config.contains("villager-random-names-enabled"))
			return config.getBoolean("villager-random-names-enabled");
		return false;
	}

	@Override
	public List<String> getRandomVillagerNames() {
		if (config.contains("villager-random-names"))
			return config.getStringList("villager-random-names");
		return new ArrayList<String>();
	}

	@Override
	public boolean isWinTitleEnabled() {
		if (config.contains("titles.win.enabled"))
			return config.getBoolean("titles.win.enabled");
		return false;
	}

	@Override
	public int getWinTitleFadeInTimeInSeconds() {
		if (config.contains("titles.win.title-fade-in-time"))
			return config.getInt("titles.win.title-fade-in-time");
		return 1;
	}

	@Override
	public int getWinTitleFadeOutTimeInSeconds() {
		if (config.contains("titles.win.title-fade-out-time"))
			return config.getInt("titles.win.title-fade-out-time");
		return 1;
	}

	@Override
	public int getWinTitleStayTimeInSeconds() {
		if (config.contains("titles.win.title-stay-time"))
			return config.getInt("titles.win.title-stay-time");
		return 15;
	}

	@Override
	public int getWinSubtitleFadeInTimeInSeconds() {
		if (config.contains("titles.win.subtitle-fade-in-time"))
			return config.getInt("titles.win.subtitle-fade-in-time");
		return 1;
	}

	@Override
	public int getWinSubtitleFadeOutTimeInSeconds() {
		if (config.contains("titles.win.subtitle-fade-out-time"))
			return config.getInt("titles.win.subtitle-fade-out-time");
		return 1;
	}

	@Override
	public int getWinSubtitleStayTimeInSeconds() {
		if (config.contains("titles.win.subtitle-stay-time"))
			return config.getInt("titles.win.subtitle-stay-time");
		return 15;
	}
	
	@Override
	public boolean isMapTitleEnabled() {
		if (config.contains("titles.map.enabled"))
			return config.getBoolean("titles.map.enabled");
		return false;
	}

	@Override
	public int getMapTitleFadeInTimeInSeconds() {
		if (config.contains("titles.map.title-fade-in-time"))
			return config.getInt("titles.map.title-fade-in-time");
		return 1;
	}

	@Override
	public int getMapTitleFadeOutTimeInSeconds() {
		if (config.contains("titles.map.title-fade-out-time"))
			return config.getInt("titles.map.title-fade-out-time");
		return 1;
	}

	@Override
	public int getMapTitleStayTimeInSeconds() {
		if (config.contains("titles.map.title-stay-time"))
			return config.getInt("titles.map.title-stay-time");
		return 2;
	}

	@Override
	public int getMapSubtitleFadeInTimeInSeconds() {
		if (config.contains("titles.map.subtitle-fade-in-time"))
			return config.getInt("titles.map.subtitle-fade-in-time");
		return 1;
	}

	@Override
	public int getMapSubtitleFadeOutTimeInSeconds() {
		if (config.contains("titles.map.subtitle-fade-out-time"))
			return config.getInt("titles.map.subtitle-fade-out-time");
		return 1;
	}

	@Override
	public int getMapSubtitleStayTimeInSeconds() {
		if (config.contains("titles.map.subtitle-stay-time"))
			return config.getInt("titles.map.subtitle-stay-time");
		return 2;
	}

	@Override
	public boolean isGoalTitleEnabled() {
		if (config.contains("titles.goal.enabled"))
			return config.getBoolean("titles.goal.enabled");
		return false;
	}

	@Override
	public int getGoalTitleFadeInTimeInSeconds() {
		if (config.contains("titles.goal.title-fade-in-time"))
			return config.getInt("titles.goal.title-fade-in-time");
		return 1;
	}

	@Override
	public int getGoalTitleFadeOutTimeInSeconds() {
		if (config.contains("titles.goal.title-fade-out-time"))
			return config.getInt("titles.goal.title-fade-out-time");
		return 1;
	}

	@Override
	public int getGoalTitleStayTimeInSeconds() {
		if (config.contains("titles.goal.title-stay-time"))
			return config.getInt("titles.goal.title-stay-time");
		return 2;
	}

	@Override
	public int getGoalSubtitleFadeInTimeInSeconds() {
		if (config.contains("titles.goal.subtitle-fade-in-time"))
			return config.getInt("titles.goal.subtitle-fade-in-time");
		return 1;
	}

	@Override
	public int getGoalSubtitleFadeOutTimeInSeconds() {
		if (config.contains("titles.goal.subtitle-fade-out-time"))
			return config.getInt("titles.goal.subtitle-fade-out-time");
		return 1;
	}

	@Override
	public int getGoalSubtitleStayTimeInSeconds() {
		if (config.contains("titles.goal.subtitle-stay-time"))
			return config.getInt("titles.goal.subtitle-stay-time");
		return 2;
	}
	
}
