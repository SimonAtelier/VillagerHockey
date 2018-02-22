package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyMenu {
	
	public void display(UUID viewer) {
		Player player = Bukkit.getPlayer(viewer);
		PlayerInventory inventory = player.getInventory();
		inventory.setItem(0, createSelectTeamItem());
		inventory.setItem(1, createAchievementsItem());
		inventory.setItem(7, createForceStartItem());
		inventory.setItem(8, createLeaveGameItem());
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
