package gateways.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import entities.Statistics;
import entities.StatisticsImpl;
import gateways.StatisticsGateway;

public class StatisticsGatewayImpl implements StatisticsGateway {

	private File getFile(UUID player) throws GatewayException {
		File file = new File("plugins/VillagerHockey/playerdata/" + player + ".yml");
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

	@Override
	public Statistics findStatistics(UUID player) {
		File file = getFile(player);

		if (!file.exists())
			throw new GatewayException("File does not exist: " + file);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		StatisticsImpl statistics = new StatisticsImpl();
		statistics.setGamesDraw(config.getInt("games-draw"));
		statistics.setGamesWon(config.getInt("games-won"));
		statistics.setGamesPlayed(config.getInt("games-played"));
		statistics.setGamesLost(config.getInt("games-lost"));
		statistics.setTotalTimePlayedInSeconds(config.getLong("total-time-played-in-seconds"));

		return statistics;
	}

	@Override
	public void saveStatistics(UUID player, Statistics statistics) throws GatewayException {
		File file = getFile(player);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		config.set("games-draw", statistics.getGamesDraw());
		config.set("games-won", statistics.getGamesWon());
		config.set("games-played", statistics.getGamesPlayed());
		config.set("games-lost", statistics.getGamesLost());
		config.set("total-time-played-in-seconds", statistics.getTotalTimePlayedInSeconds());
		
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		}
	}

}
