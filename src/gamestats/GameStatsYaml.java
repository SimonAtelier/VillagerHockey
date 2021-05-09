package gamestats;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import achievements.Achievement;
import context.Context;
import gateways.impl.GatewayException;

public class GameStatsYaml {
	
	private File getFile(UUID uniquePlayerId) throws GatewayException {
		File file = new File("plugins/VillagerHockey/playerdata/stats/" + uniquePlayerId + ".yml");
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				throw new GatewayException(e);
			}
		}
		return file;
	}

	public void save(UUID uniquePlayerId) {
		File file = getFile(uniquePlayerId);
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		GameStatistic gameStatistic = Context.gameStatisticGateway.findByPlayerId(uniquePlayerId);
		
		for (String key : gameStatistic.getKeys()) {
			config.set(key, gameStatistic.getValue(key));
		}

		List<String> ids = new ArrayList<String>();
		List<Achievement> unlockedAchievements = Context.achievementSystem.findUnlockedAchievementsOfPlayer(uniquePlayerId);
		for (Achievement achievement : unlockedAchievements) {
			ids.add(achievement.getId());
		}
		config.set("achievements", ids);
		
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		}
	}
	
	public GameStatistic load(UUID uniquePlayerId) {
		File file = getFile(uniquePlayerId);
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		GameStatistic gameStatistic = new GameStatistic();
		gameStatistic.setUniquePlayerId(uniquePlayerId);
		
		List<String> keys = new ArrayList<String>(); 
		keys = (List<String>) config.getList("achievements", keys);
		for (String key : keys)
			Context.achievementSystem.unlockAchievementForPlayer(uniquePlayerId, key);
		
		for (String key : StatisticKeys.KEYS) {
			gameStatistic.setValue(key, config.getInt(key));
		}
		
		return gameStatistic;
	}
	
}
