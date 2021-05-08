package usecases.savegame.validation;

import java.util.List;

import entities.Team;
import game.Game;
import usecases.savegame.SaveGame.SaveGameResponse;
import usecases.savegame.validation.SaveGameValidator.SaveGameValidationRule;

public class AmountOfTeamSpawnsShouldBeEqualRule implements SaveGameValidationRule {

	private Game game;
	
	@Override
	public boolean isValid(Game game, SaveGameResponse response) {
		setGame(game);
		
		if (teamsIsEmpty()) {
			return false;
		}
		
		if (amountOfTeamSpawnsIsNotEqual()) {
			response.onCannotSaveAmountOfTeamSpawnsIsNotEqual();
			return false;
		}
		
		return true;
	}
	
	private boolean teamsIsEmpty() {
		List<Team> teams = getGame().getTeams().findAllTeams();
		return teams.isEmpty();
	}
	
	private boolean amountOfTeamSpawnsIsNotEqual() {
		List<Team> teams = getGame().getTeams().findAllTeams();
		int size = teams.get(0).getMaximumSize();
		boolean equalSize = true;
		for (Team team : teams) {
			equalSize &= (size == team.getMaximumSize());
		}
		return !equalSize;
	}
	
	private Game getGame() {
		return game;
	}
	
	private void setGame(Game game) {
		this.game = game;
	}
	
}
