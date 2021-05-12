package entities.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationYaml implements Configuration {

	private YamlConfiguration config;

	public ConfigurationYaml(YamlConfiguration config) {
		this.config = config;
	}
	
	@Override
	public boolean isSpectatorMessagesEnabled() {
		if (config.contains("spectator-messages-enabled"))
			return config.getBoolean("spectator-messages-enabled");
		return false;
	}

	@Override
	public int getSpectatorMessagesRadius() {
		if (config.contains("spectator-messages-radius"))
			return config.getInt("spectator-messages-radius");
		return 0;
	}

	@Override
	public String getPrefix() {
		if (config.contains("chat-prefix"))
			return config.getString("chat-prefix");
		return "[VillagerHockey]";
	}

	@Override
	public String getChatWithAllLabel() {
		if (config.contains("chat-with-all-label"))
			return config.getString("chat-with-all-label");
		return "!";
	}

	@Override
	public int getLobbyTime() {
		if (config.contains("lobby-time"))
			return config.getInt("lobby-time");
		return 60;
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
	public boolean isAchievementsEnabled() {
		if (config.contains("achievements-enabled"))
			return config.getBoolean("achievements-enabled");
		return false;
	}

	@Override
	public boolean isCosmeticsEnabled() {
		if (config.contains("cosmetics-enabled"))
			return config.getBoolean("cosmetics-enabled");
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
	public String getScoreboardTitle() {
		if (config.contains("scoreboard-title"))
			return config.getString("scoreboard-title");
		return "VILLAGER HOCKEY";
	}

	@Override
	public String getScoreboardServerAdress() {
		if (config.contains("scoreboard-server"))
			return config.getString("scoreboard-server");
		return "foo.your-server.bar";
	}
	
}
