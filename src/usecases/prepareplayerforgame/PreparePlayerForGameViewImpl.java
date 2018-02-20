package usecases.prepareplayerforgame;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import view.hockeysticks.HockeySticksView;
import view.hockeysticks.HockeySticksViewImpl;
import view.impl.ScoreView;

public class PreparePlayerForGameViewImpl implements PreparePlayerForGameView {

	private ScoreView scoreView;
	private HockeySticksView hockeySticksView;
	
	public PreparePlayerForGameViewImpl() {
		scoreView = new ScoreView();
		hockeySticksView = new HockeySticksViewImpl();
	}
	
	@Override
	public void displayHockeySticks(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		PlayerInventory inventory = player.getInventory();
		
		for (int i = 0; i < 9; i++) {
			inventory.clear(i);
		}
		
		hockeySticksView.displayHockeySticks(uniquePlayerId);
	}

	@Override
	public void displayScores(UUID uniquePlayerId) {
		scoreView.display(uniquePlayerId);
	}

}
