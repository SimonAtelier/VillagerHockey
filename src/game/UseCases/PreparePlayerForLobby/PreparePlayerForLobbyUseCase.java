package game.UseCases.PreparePlayerForLobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import context.Context;
import gateways.InventoryGateway;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;
import view.impl.HockeySticksViewImpl;

public class PreparePlayerForLobbyUseCase implements PreparePlayerForLobby {

	@Override
	public void execute(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		PlayerInventory inventory = (PlayerInventory) player.getInventory();
		saveAndClearInventory(player);
		savePlayerData(player);
		setPlayerLobbyData(player);
//		addHockeySticks(player.getUniqueId());
		addTeamSelectItem(inventory);
//		addAchievementsItem(inventory);
	}
	
	private void addTeamSelectItem(PlayerInventory inventory) {
		ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Teamauswahl (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(0, itemStack);
	}
	
	private void addAchievementsItem(PlayerInventory inventory) {
		ItemStack itemStack = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Achievements (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(1, itemStack);
	}
	
	private void setPlayerLobbyData(Player player) {
		player.setGameMode(GameMode.ADVENTURE);
		player.setExp(0);
		player.setLevel(0);
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		removeAllPotionEffects(player);
	}
	
	private void addHockeySticks(UUID uniquePlayerId) {
		new HockeySticksViewImpl().displayHockeySticks(uniquePlayerId);
	}
	
	private void saveAndClearInventory(Player player) {
		UUID uniquePlayerId = player.getUniqueId();
		InventoryGateway inventoryGateway = Context.inventoryGateway;
		inventoryGateway.save(uniquePlayerId);
		inventoryGateway.clearInventoryOfPlayer(uniquePlayerId);		
	}
	
	private void savePlayerData(Player player) {
		PlayerDataGateway gateway = new PlayerDataGatewayYaml();
		gateway.save(player.getUniqueId());
	}
	
	private void removeAllPotionEffects(Player player) {
		for (PotionEffect effect : player.getActivePotionEffects()) {
			if (player.hasPotionEffect(effect.getType()))
				player.removePotionEffect(effect.getType());
		}
	}
		
}
