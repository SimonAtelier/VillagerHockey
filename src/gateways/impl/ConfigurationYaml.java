package gateways.impl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import gateways.Configuration;

public class ConfigurationYaml implements Configuration {

	private YamlConfiguration config;

	public ConfigurationYaml(YamlConfiguration config) {
		this.config = config;
	}
	
	@Override
	public String getPrefix() {
		if (config.contains("prefix")) {
			return config.getString("prefix");
		}
		return "[VillagerHockey]";
	}

	@Override
	public int getLobbyTime() {
		if (config.contains("lobby.time"))
			return config.getInt("lobby.time");
		return 60;
	}

	@Override
	public int getLobbyGameMode() {
		if (config.contains("lobby.gamemode"))
			return config.getInt("lobby.gamemode");
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
		if (config.contains("villager.name"))
			return config.getString("villager.name");
		return "";
	}

	@Override
	public boolean isVillagerAIEnabled() {
		if (config.contains("villager.ai"))
			return config.getBoolean("villager.ai");
		return false;
	}

	@Override
	public boolean isUseRandomVillagerNamesEnabled() {
		if (config.contains("villager.names.enabled"))
			return config.getBoolean("villager.names.enabled");
		return false;
	}

	@Override
	public List<String> getRandomVillagerNames() {
		if (config.contains("villager.names.list"))
			return config.getStringList("villager.names.list");
		return new ArrayList<String>();
	}
	
}
