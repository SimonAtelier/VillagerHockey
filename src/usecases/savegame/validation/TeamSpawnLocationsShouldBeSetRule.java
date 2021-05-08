package usecases.savegame.validation;

import entities.Team;
import game.Game;
import usecases.savegame.SaveGame.SaveGameResponse;
import usecases.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class TeamSpawnLocationsShouldBeSetRule implements SaveGameValidationRule {

	private Game game;
	
	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		this.game = game;
		if (teamSpawnLocationsMissing()) {
			response.onCannotSaveSpawnLocationsMissing();
			return false;
		}
		return true;
	}
	
	private boolean teamSpawnLocationsMissing() {		
		for (Team team : game.getTeams().findAllTeams()) {
			if (team.getSpawnLocations().isEmpty())
				return true;
		}
		return false;
	}

}
