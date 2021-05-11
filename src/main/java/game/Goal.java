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

	public GoalResponse check() {
		if (!game.isGoalsEnabled())
			return new GoalResponse(false, game, team);

		VillagerSpawner villagerSpawner = game.getVillagerSpawner();
		Villager villager = villagerSpawner.getVillager();

		if (villager == null)
			return new GoalResponse(false, game, team);

		Location location = villager.getLocation();
		if (contains(LocationConvert.toEntityLocation(location))) {
			return new GoalResponse(true, game, team);
		}
		
		return new GoalResponse(false, game, team);
	}

	public String getTeam() {
		return team;
	}

	public class GoalResponse {

		private boolean sored;
		private Game game;
		private String team;

		public GoalResponse(boolean sored, Game game, String team) {
			this.sored = sored;
			this.game = game;
			this.team = team;
		}

		public boolean isSored() {
			return sored;
		}

		public Game getGame() {
			return game;
		}

		public String getTeam() {
			return team;
		}

	}

}
