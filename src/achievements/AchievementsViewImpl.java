package achievements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import context.Context;
import main.MainPlugin;

public class AchievementsViewImpl implements Listener {

	private int slots = 6 * 9;
	private UUID viewer;
	private Inventory inventory;
	
	public AchievementsViewImpl(UUID viewer) {
		this.viewer = viewer;
	}
	
	public void display() {
		Player bukkitPlayer = Bukkit.getPlayer(viewer);
		inventory = Bukkit.createInventory(null, slots, "Villager Hockey Achievements");
		createItems();
		Bukkit.getServer().getPluginManager().registerEvents(this, MainPlugin.getInstance());
		bukkitPlayer.openInventory(inventory);
	}
	
	@EventHandler
	public void onInventoryClicked(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;
		if (!e.getInventory().equals(inventory))
			return;
		Player player = (Player) e.getWhoClicked();
		if (player.getUniqueId() != viewer)
			return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent e) {
		if (!e.getPlayer().getUniqueId().equals(viewer))
			return;
		if (!e.getInventory().equals(inventory))
			return;
		HandlerList.unregisterAll(this);
	}
	
	private void createItems() {
		AchievementSystem achievementSystem = Context.achievementSystem;
		
		for (Achievement achievement : achievementSystem.findAllAchievements()) {
			if (achievementSystem.isUnlockedForPlayer(achievement.getId(), viewer)) {
				inventory.addItem(createUnlockedAchievementItem(achievement));
			} else {
				inventory.addItem(createLockedAchievementItem(achievement));
			}
		}
	}
	
	private ItemStack createLockedAchievementItem(Achievement achievement) {
		Material material = Material.COAL;
		ItemStack itemStack = new ItemStack(material, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(createLockedLore(achievement));
		itemMeta.setDisplayName("§4" + achievement.getName());
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack createUnlockedAchievementItem(Achievement achievement) {
		Material material = Material.DIAMOND;
		ItemStack itemStack = new ItemStack(material, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(createUnlockedLore(achievement));
		itemMeta.setDisplayName("§2" + achievement.getName());
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private List<String> createLockedLore(Achievement achievement) {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§f" + achievement.getDescription());
		lore.add("");
		lore.add("§7Reward:");
		lore.add("§8+§e" + achievement.getPoints() + " §7Achievement Points");
		lore.add("");
		lore.add("§4Achievement locked!");
		return lore;
	}
	
	private List<String> createUnlockedLore(Achievement achievement) {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§f" + achievement.getDescription());
		lore.add("");
		lore.add("§7Reward:");
		lore.add("§8+§e" + achievement.getPoints() + " §7Achievement Points");
		lore.add("");
		lore.add("§2Achievement unlocked!");
		return lore;
	}
	 
}
