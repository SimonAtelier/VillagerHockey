package game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

import entities.Region;
import main.MainPlugin;
import util.LocationConvert;

public class Goal extends Region {

	private int taskId;
	private boolean running;
	private Game game;
	private String team;

	public Goal(Game game, String team) {
		this.game = game;
		this.team = team;
	}

	public void start() {
		if (running)
			return;
		running = true;
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainPlugin.getInstance(), new Runnable() {

			@Override
			public void run() {
				VillagerSpawner villagerSpawner = game.getVillagerSpawner();
				Villager villager = villagerSpawner.getVillager();

				if (villager == null)
					return;

				Location location = villager.getLocation();
				if (contains(LocationConvert.toEntityLocation(location))) {
					game.getVillagerSpawner().removeVillager();
					game.onTeamScored(team);
				}
			}
		}, 0, 5);
	}

	public void stop() {
		if (!running)
			return;
		running = false;
		Bukkit.getScheduler().cancelTask(taskId);
	}

	public String getTeam() {
		return team;
	}

}
