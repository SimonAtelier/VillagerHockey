package usecases.hockey.savegame.validation;

import game.Game;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.hockey.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class VillagerSpawnLocationShouldBeSetRule implements SaveGameValidationRule {

	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		if (game.getVillagerSpawner().getVillagerSpawnLocation() != null)
			return true;
		response.onCannotSaveGameNoVillagerSpawnSet();
		return false;
	}

}
