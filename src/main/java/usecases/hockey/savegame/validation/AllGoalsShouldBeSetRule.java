package usecases.hockey.savegame.validation;

import entities.Team;
import game.Game;
import game.hockey.HockeyGameCycle;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.hockey.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class AllGoalsShouldBeSetRule implements SaveGameValidationRule {

	private Game game;
	
	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		this.game = game;

		if (notAllGoalsSet()) {
			response.onCannotSaveNotAllGoalsSet();
			return false;
		}
		
		return true;
	}
	
	private boolean notAllGoalsSet() {
		HockeyGameCycle hockey = (HockeyGameCycle) game.getGameCycle();
		for (Team team : game.getTeams().findAllTeams()) {
			if (hockey.findGoalOfTeam(team.getName()) == null)
				return true;
		}
		return false;
	}

}
