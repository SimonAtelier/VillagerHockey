package usecases.savegame.validation;

import game.Game;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class NumberOfTeamsShouldBeTwoRule implements SaveGameValidationRule {

	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		if (game.getTeams().getNumberOfTeams() == 2)
			return true;
		response.onCannotSaveNumberOfTeamsIsNotTwo();
		return false;
	}

}
