package gateways.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import gateways.InventoryGateway;

public class InventoryGatewayYaml implements InventoryGateway {

	private String ymlPath = "inventory";
	private String path;
	
	public InventoryGatewayYaml(String path) {
		this.path = path;
	}
	
	private File getFile(Player player) throws GatewayException {
		File file = new File(path
				+ player.getUniqueId() + ".yml");
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
	public void saveInventoryOfPlayer(UUID uniquePlayerId) throws GatewayException {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		Inventory inventory = player.getInventory();
		ItemStack[] itemStacks = new ItemStack[inventory.getSize()];
		
		for (int i = 0; i < itemStacks.length; i++) {
			ItemStack itemStack = inventory.getItem(i);
			itemStacks[i] = itemStack;
		}

		File file = getFile(player);
		
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set(ymlPath, itemStacks);

		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new GatewayException(e);
		}
	}

	@Override
	public void loadInventoryOfPlayer(UUID uniquePlayerId) throws GatewayException {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		File file = getFile(player);
		
		if (!file.exists())
			throw new GatewayException("File does not exist: " + file);
		
		Inventory inventory = player.getInventory();
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		inventory.clear();
		
		List<?> itemStacks = config.getList(ymlPath);
		
		for (int i = 0; i < itemStacks.size(); i++) {
			ItemStack itemStack = (ItemStack) itemStacks.get(i);
			inventory.setItem(i, itemStack);
		}
	}

	@Override
	public void clearInventoryOfPlayer(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		PlayerInventory inventory = player.getInventory();
		inventory.clear();
	}

}
