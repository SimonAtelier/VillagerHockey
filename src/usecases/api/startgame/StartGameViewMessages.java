package usecases.api.startgame;

public interface StartGameViewMessages {

	static final String START_GAME_NO_PERMISSION = "Du hast keine Berechtigung ein Spiel zu starten.";
	
	static final String START_GAME_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";
	
	static final String START_GAME_ALREADY_STARTED = "Das Spiel '$game$' wurde bereits gestartet.";
	
	static final String START_GAME_SUCCESSFULLY_STARTED = "Das Spiel '$game$' wurde erfolgreich gestartet.";
	
}
