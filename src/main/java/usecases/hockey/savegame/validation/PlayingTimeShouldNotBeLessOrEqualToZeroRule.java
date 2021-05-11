package usecases.hockey.savegame.validation;

import game.Game;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.hockey.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class PlayingTimeShouldNotBeLessOrEqualToZeroRule implements SaveGameValidationRule {

	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		if (game.getPlayingTimeInSeconds() > 0)
			return true;
		response.onCannotSavePlayingTimeIsLessOrEqualToZero();
		return false;
	}

}
