package game;

import org.bukkit.Location;
import org.bukkit.entity.Villager;

import entities.Region;
import util.LocationConvert;

public class Goal extends Region {

	private Game game;
	private String team;

	public Goal(Game game, String team) {
		this.game = game;
		this.team = team;
	}
	
	public void check() {
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

	public String getTeam() {
		return team;
	}

}
