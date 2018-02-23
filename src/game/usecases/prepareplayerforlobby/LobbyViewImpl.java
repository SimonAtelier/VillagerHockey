package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyMenuViewImpl implements LobbyMenuView {
	
	PlayerInventory inventory;
	
	public LobbyMenuViewImpl(UUID viewer) {
		setInventoryOfPlayer(Bukkit.getPlayer(viewer));
	}
	
	private void setInventoryOfPlayer(Player player) {
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

	private ItemStack createSelectTeamItem() {
		ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Teamauswahl (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);	
		return itemStack;
	}
	
	private ItemStack createAchievementsItem() {
		ItemStack itemStack = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Achievements (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack createForceStartItem() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Start erzwingen (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack createLeaveGameItem() {
		ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Spiel verlassen (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
}
