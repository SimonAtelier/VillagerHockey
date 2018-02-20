package gateways.impl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import gateways.configuration.Configuration;

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
	public int getLobbyGameMode() {
		if (config.contains("lobby-gamemode"))
			return config.getInt("lobby-gamemode");
		return 0;
	}
	
	@Override
	public boolean isMapTitleEnabled() {
		if (config.contains("titles.map.enabled"))
			return config.getBoolean("titles.map.enabled");
		return true;
	}

	@Override
	public boolean isGoalTitleEnabled() {
		if (config.contains("titles.goal.enabled"))
			return config.getBoolean("titles.goal.enabled");
		return true;
	}

	@Override
	public String getGameListTitle() {
		if (config.contains("gamelist.title"))
			return config.getString("gamelist.title");
		return "====[ VillagerHockey Game List ]====";
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
	
}
