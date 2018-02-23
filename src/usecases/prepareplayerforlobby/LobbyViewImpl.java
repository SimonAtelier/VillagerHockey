package usecases.prepareplayerforlobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class LobbyViewImpl implements LobbyView {
	
	Player player;
	PlayerInventory inventory;
	
	public LobbyViewImpl(UUID viewer) {
		player = Bukkit.getPlayer(viewer);
		inventory = player.getInventory();
	}

	@Override
	public void displaySelectTeam() {
		inventory.setItem(0, createSelectTeamItem());
	}

	@Override
	public void displayForceStart() {
		inventory.setItem(7, createForceStartItem());
	}

	@Override
	public void displayLeaveGame() {
		inventory.setItem(8, createLeaveGameItem());
	}

	@Override
	public void displayAchievements() {
		inventory.setItem(1, createAchievementsItem());
	}
	
	@Override
	public void displayGameMode(String gameMode) {
		player.setGameMode(GameMode.valueOf(gameMode));
	}

	@Override
	public void displayLevel(int level) {
		player.setLevel(level);
	}

	@Override
	public void displayFoodLevel(int foodLevel) {
		player.setFoodLevel(foodLevel);
	}
	
	@Override
	public void displayMaxHealth() {
		player.setHealth(player.getMaxHealth());
	}
	
	@Override
	public void displayExperience(int experience) {
		player.setExp(experience);
	}

	@Override
	public void displayClearAllPotionEffects() {
		removeAllPotionEffects();
	}

	private void removeAllPotionEffects() {
		for (PotionEffect effect : player.getActivePotionEffects()) {
			if (player.hasPotionEffect(effect.getType()))
				player.removePotionEffect(effect.getType());
		}
	}

	private ItemStack createSelectTeamItem() {
		ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(LobbyViewMessages.LOBBY_SELECT_TEAM_DISPLAY_NAME);
		itemStack.setItemMeta(itemMeta);	
		return itemStack;
	}
	
	private ItemStack createAchievementsItem() {
		ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(LobbyViewMessages.LOBBY_ACHIEVEMENTS_DISPLAY_NAME);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack createForceStartItem() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(LobbyViewMessages.LOBBY_FORCE_START_DISPLAY_NAME);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack createLeaveGameItem() {
		ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(LobbyViewMessages.LOBBY_LEAVE_GAME_DISPLAY_NAME);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
}
