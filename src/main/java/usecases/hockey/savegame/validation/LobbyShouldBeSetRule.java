package usecases.hockey.savegame.validation;

import game.Game;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.hockey.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class LobbyShouldBeSetRule implements SaveGameValidationRule {

	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		if (game.getLobby() != null)
			return true;
		response.onCannotSaveGameNoLobbySet();
		return false;
	}

}
