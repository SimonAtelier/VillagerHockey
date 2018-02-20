package view.hockeysticks;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class HockeySticksViewImpl implements HockeySticksView {

	private int fishSlot;
	private int stickSlot;
	private String fishDisplayName;
	private String stickDisplayName;
	
	public HockeySticksViewImpl() {
		fishSlot = 0;
		stickSlot = 1;
		fishDisplayName = "Vergammelter Fisch";
		stickDisplayName = "Kaputter Zauberstab";
	}
	
	@Override
	public void displayHockeySticks(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		PlayerInventory inventory = player.getInventory();
		addRawFishWithKnockbackTwoEnchantment(inventory);
		addStickWithKnockbackOneEnchantment(inventory);
	}
	
	@Override
	public void hideHockeySticks(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		PlayerInventory inventory = player.getInventory();
		inventory.setItem(fishSlot, null);
		inventory.setItem(stickSlot, null);
	}
	
	private void addRawFishWithKnockbackTwoEnchantment(PlayerInventory inventory) {
		ItemStack itemStack = new ItemStack(Material.RAW_FISH, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
		itemMeta.setDisplayName(fishDisplayName);
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(fishSlot, itemStack);
	}

	private void addStickWithKnockbackOneEnchantment(PlayerInventory inventory) {
		ItemStack itemStack = new ItemStack(Material.STICK, 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
		itemMeta.setDisplayName(stickDisplayName);
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(stickSlot, itemStack);
	}
	
}
