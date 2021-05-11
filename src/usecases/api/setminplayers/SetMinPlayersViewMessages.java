package usecases.api.setminplayers;

public interface SetMinPlayersViewMessages {

	static final String SET_MIN_PLAYERS_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht,";

	static final String SET_MIN_PLAYERS_NOT_A_NUMBER = "'$number$' ist keine g�ltige Zahl.";

	static final String SET_MIN_PLAYERS_SUCCESS = "Die mininmale Anzahl '$number$' der Spieler f�r das Spiel '$game$' wurde erfolgreich gesetzt.";

	static final String SET_MIN_PLAYERS_NO_PERMISSION = "Du hast keine Berechtigung die minimale Spielerzahl zu setzen.";
	
}
