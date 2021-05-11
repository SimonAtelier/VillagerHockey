package usecases.api.stopgame;

public interface StopGameViewMessages {

	static final String STOP_GAME_NO_PERMISSION = "Du hast keine Berechtigung ein Spiel zu stoppen.";
	
	static final String STOP_GAME_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";
	
	static final String STOP_GAME_ALREADY_STOPPED = "Das Spiel '$game$' ist bereits gestoppt.";
	
	static final String STOP_GAME_SUCCESSFULLY_STOPPED = "Das Spiel '$game$' wurde erfolgreich gestoppt.";
	
	static final String STOP_GAME_STOPPING = "Das Spiel '$game$' wurde durch einen Administrator gestoppt.";
	
}
