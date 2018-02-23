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
	
	public HockeySticksViewImpl() {
		fishSlot = 0;
		stickSlot = 1;
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
		ItemStack itemStack = new ItemStack(Material.RAW_FISH);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
		itemMeta.setDisplayName(HockeySticksViewMessages.HOCKEY_STICKS_FISH_DISPLAY_NAME);
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(fishSlot, itemStack);
	}

	private void addStickWithKnockbackOneEnchantment(PlayerInventory inventory) {
		ItemStack itemStack = new ItemStack(Material.STICK);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
		itemMeta.setDisplayName(HockeySticksViewMessages.HOCKEY_STICKS_STICK_DISPLAY_NAME);
		itemStack.setItemMeta(itemMeta);
		inventory.setItem(stickSlot, itemStack);
	}
	
}
