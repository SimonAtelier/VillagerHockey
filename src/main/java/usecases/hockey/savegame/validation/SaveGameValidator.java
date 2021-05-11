package usecases.hockey.savegame.validation;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import usecases.hockey.savegame.SaveGame.SaveGameResponse;

public class SaveGameValidator {

	private boolean invalid;
	private Game game;
	private SaveGameResponse response;
	private List<SaveGameValidationRule> validationRules;
	
	public SaveGameValidator() {
		validationRules = new ArrayList<SaveGameValidationRule>();
	}
	
	public void addValidationRule(SaveGameValidationRule validationRule) {
		validationRules.add(validationRule);
	}
	
	public void validate() {
		setValid();
		for (SaveGameValidationRule rule : validationRules) {
			if (!rule.isValid(getGame(), getResponse())) {
				setInvalid();
			}
		}
	}
	
	public boolean isInvalid() {
		return invalid;
	}
	
	private void setValid() {
		invalid = false;
	}
	
	private void setInvalid() {
		invalid = true;
	}
	
	private Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	private SaveGameResponse getResponse() {
		return response;
	}
	
	public void setResponse(SaveGameResponse response) {
		this.response = response;
	}
	
	public interface SaveGameValidationRule {
		
		boolean isValid(Game game, SaveGameResponse response);
		
	}
	
}
