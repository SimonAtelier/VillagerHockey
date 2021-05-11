package usecases.achievements.displayachievements;

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

public class DisplayAchievementsViewImpl implements DisplayAchievementsView, Listener {

	private int slots = 6 * 9;
	private UUID viewer;
	private Inventory inventory;
	
	public DisplayAchievementsViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	@Override
	public void display(List<AchievementResponseItem> responseItems) {
		Player bukkitPlayer = Bukkit.getPlayer(viewer);
		inventory = Bukkit.createInventory(null, slots, "Villager Hockey Achievements");
		createItems(responseItems);
		Bukkit.getServer().getPluginManager().registerEvents(this, MainPlugin.getInstance());
		bukkitPlayer.openInventory(inventory);
	}
	
	@Override
	public void displayMessage(String message) {
		Context.messageView.displayMessage(viewer, message);
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
	
	private void createItems(List<AchievementResponseItem> responseItems) {
		for (AchievementResponseItem responseItem : responseItems) {
			if (responseItem.isUnlocked()) {
				inventory.addItem(createUnlockedAchievementItem(responseItem));
			} else {
				inventory.addItem(createLockedAchievementItem(responseItem));
			}
		}
	}
	
	private ItemStack createLockedAchievementItem(AchievementResponseItem responseItem) {
		Material material = Material.COAL;
		ItemStack itemStack = new ItemStack(material, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(createLockedLore(responseItem));
		itemMeta.setDisplayName("§4" + responseItem.getName());
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack createUnlockedAchievementItem(AchievementResponseItem responseItem) {
		Material material = Material.CLAY_BALL;
		ItemStack itemStack = new ItemStack(material, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(createUnlockedLore(responseItem));
		itemMeta.setDisplayName("§2" + responseItem.getName());
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private List<String> createLockedLore(AchievementResponseItem responseItem) {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§f" + responseItem.getDescription());
		lore.add("");
		if (responseItem.isProgress())
			lore.add("§7Progress: §e" + responseItem.getCurrentProgress() + "§7/§e" + responseItem.getActivationValuesSum());
		lore.add("§7Reward:");
		lore.add("§8+§e" + responseItem.getPoints() + " §7Achievement Points");
		lore.add("");
		lore.add("§4Achievement locked!");
		return lore;
	}
	
	private List<String> createUnlockedLore(AchievementResponseItem responseItem) {
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§f" + responseItem.getDescription());
		lore.add("");
		if (responseItem.isProgress())
			lore.add("§7Progress: §e" + responseItem.getCurrentProgress() + "§7/§e" + responseItem.getActivationValuesSum());
		lore.add("§7Reward:");
		lore.add("§8+§e" + responseItem.getPoints() + " §7Achievement Points");
		lore.add("");
		lore.add("§2Achievement unlocked!");
		return lore;
	}

}
