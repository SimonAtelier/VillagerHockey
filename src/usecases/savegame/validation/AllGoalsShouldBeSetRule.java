package usecases.savegame.validation;

import entities.Team;
import game.Game;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.savegame.validation.SaveGameValidator.SaveGameValidationRule;

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
		for (Team team : game.getTeams().findAllTeams()) {
			if (game.findGoalOfTeam(team.getName()) == null)
				return true;
		}
		return false;
	}

}
