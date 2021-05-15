package game.hockey;

import entities.Location;
import entities.Region;
import game.Game;

public class Goal extends Region {

	private Game game;
	private String team;

	public Goal(Game game, String team) {
		this.game = game;
		this.team = team;
	}

	public GoalResponse check(Location location) {
		if (contains(location))
			return new GoalResponse(true, game, team);
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
