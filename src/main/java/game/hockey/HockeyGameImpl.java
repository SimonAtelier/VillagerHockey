package game.hockey;

import java.util.ArrayList;
import java.util.List;

import game.AbstractGame;
import game.Game;

public class HockeyGameImpl extends AbstractGame implements HockeyGame {

	private List<Goal> goals;

	public HockeyGameImpl(String name) {
		super(name);

		goals = new ArrayList<Goal>();
		setGameCycle(new HockeyGameCycle((Game) this, this));
	}
	
	public Goal findGoalOfTeam(String team) {
		for (int i = 0; i < goals.size(); i++) {
			Goal goal = goals.get(i);
			if (goal.getTeam().equals(team)) {
				return goal;
			}
		}
		return null;
	}

	@Override
	public void addGoal(Goal goal) {
		if (goal == null)
			return;
		goals.add(goal);
	}

}
