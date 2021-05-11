package gateways.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import gateways.PlayerDataGateway;

public class PlayerDataGatewayYaml implements PlayerDataGateway {

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
	public void save(UUID uniquePlayerId) throws GatewayException {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		File file = getFile(player);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		config.set("level", player.getLevel());
		config.set("foodlevel", player.getFoodLevel());
		config.set("health", player.getHealth());
		config.set("exp", player.getExp());
		config.set("gamemode", player.getGameMode().toString());
		config.set("location", player.getLocation());

		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		}
	}

	@Override
	public void load(UUID uniquePlayerId) throws GatewayException {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		File file = getFile(player);

		if (!file.exists())
			throw new GatewayException("File does not exist: " + file);

		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		player.setLevel(config.getInt("level"));
		player.setFoodLevel(config.getInt("foodlevel"));
		player.setHealth(config.getDouble("health"));
		player.setExp((float) config.getDouble("exp"));
		player.setGameMode(GameMode.valueOf(config.getString("gamemode")));
		player.teleport((Location) config.get("location"));
	}

}
