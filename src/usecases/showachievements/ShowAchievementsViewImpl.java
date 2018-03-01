package usecases.showachievements;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShowAchievementsViewImpl implements ShowAchievementsView {

	private UUID viewer;
	private Inventory inventory;

	public ShowAchievementsViewImpl(UUID viewer) {
		this.viewer = viewer;
	}

	public void displayAchievements(List<String> achievements) {
		createInventory(achievements);
		Player player = Bukkit.getPlayer(viewer);
		player.openInventory(inventory);
	}

	private void createInventory(List<String> achievements) {
		int slots = 36;
		inventory = Bukkit.createInventory(null, slots,
				ShowAchievementsViewMessages.SHOW_ACHIEVEMENTS_MENU_TITLE);
		for (String achievement : achievements) {
			inventory.addItem(createAchievementItem(achievement, true));
		}
	}

	private ItemStack createAchievementItem(String displayName, boolean unlocked) {
		ItemStack itemStack = new ItemStack(unlocked ? Material.MAGMA_CREAM : Material.FIREWORK_CHARGE);
		ItemMeta itemMeta = itemStack.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("Achievement description line 0");
		lore.add("Achievement description line 1");
		itemMeta.setDisplayName(displayName);
		itemMeta.setLore(lore);
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

}
