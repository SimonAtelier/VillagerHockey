package usecases.hockey.savegame.validation;

import game.Game;
import game.hockey.HockeyGameCycle;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;
import usecases.hockey.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class VillagerSpawnLocationShouldBeSetRule implements SaveGameValidationRule {

	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		HockeyGameCycle hockey = (HockeyGameCycle) game.getGameCycle();
		if (hockey.getVillagerSpawner().getVillagerSpawnLocation() != null)
			return true;
		response.onCannotSaveGameNoVillagerSpawnSet();
		return false;
	}

}
