package gateways.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import entities.Statistics;
import entities.StatisticsImpl;
import gateways.StatisticsGateway;

public class StatisticsGatewayImpl implements StatisticsGateway {

	private File getFile(Player player) throws GatewayException {
		File file = new File("plugins/VillagerHockey/playerdata/" + player.getUniqueId() + ".yml");
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
		Player bukkitPlayer = Bukkit.getPlayer(player);
		File file = getFile(bukkitPlayer);

		if (!file.exists())
			throw new GatewayException("File does not exist: " + file);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		StatisticsImpl statistics = new StatisticsImpl();
		statistics.setGamesDraw(config.getInt("games-draw"));
		statistics.setGamesWon(config.getInt("games-won"));
		statistics.setGamesPlayed(config.getInt("games-played"));
		
		return statistics;
	}

}
