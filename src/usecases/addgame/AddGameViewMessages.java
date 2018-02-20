package usecases.addgame;

public interface AddGameViewMessages {

	static final String ADD_GAME_GAME_ALREADY_EXISTS = "Ein Spiel mit dem Namen '$game$' existiert bereits.";
	
	static final String ADD_GAME_SUCCESSFULLY_ADDED_GAME = "Das Spiel '$game$' wurde erfolgreich erstellt.";
	
	static final String ADD_GAME_INVALID_NAME = "Der angegebene Name ist ungültig.";
	
	static final String ADD_GAME_NO_PERMISSION = "Du hast keine Berechtigung ein Spiel hinzuzufügen.";
	
}
