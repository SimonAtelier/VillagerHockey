package usecases.encaps.prepareplayerforgame;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import view.hockeysticks.HockeySticksView;
import view.hockeysticks.HockeySticksViewImpl;
import view.score.HockeyScoreView;

public class PreparePlayerForGameViewImpl implements PreparePlayerForGameView {

	private HockeyScoreView scoreView;
	private HockeySticksView hockeySticksView;
	
	public PreparePlayerForGameViewImpl() {
		scoreView = new HockeyScoreView();
		hockeySticksView = new HockeySticksViewImpl();
	}
	
	@Override
	public void displayHockeySticks(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);		
		player.setLevel(0);
		
		PlayerInventory inventory = player.getInventory();
		
		for (int i = 0; i < 9; i++) {
			inventory.clear(i);
		}
		
		inventory.setItem(8, createLeaveGameItem());
		hockeySticksView.displayHockeySticks(uniquePlayerId);
	}
	
	private ItemStack createLeaveGameItem() {
		ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Spiel verlassen (Rechtsklick)");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	@Override
	public void displayScores(UUID uniquePlayerId) {
		scoreView.display(uniquePlayerId);
	}

}
