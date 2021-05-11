package usecases.api.setplayingtime;

public interface SetPlayingTimeViewMessages {

	static final String SET_PLAYING_TIME_NO_PERMISSION = "Du hast keine Berechtigung die Dauer des Spiels zu setzen.";
	
	static final String SET_PLAYING_TIME_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht,";

	static final String SET_PLAYING_TIME_NOT_A_NUMBER = "'$number$' ist keine g�ltige Zahl.";

	static final String SET_PLAYING_TIME_SUCCESS = "Die Spielzeit f�r das Spiel '$game$' wurde auf '$number$' Sekunden gesetzt.";

}
