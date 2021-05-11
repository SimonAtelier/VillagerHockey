package usecases.api.setlobby;

public interface SetLobbyViewMessages {

	static final String SET_LOBBY_NO_GAME_WITH_SUCH_NAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";
	
	static final String SET_LOBBY_SUCCESSFULLY_SET = "Die Lobby von '$game$' wurde erfolgreich gesetzt.";
	
	static final String SET_LOBBY_NO_PERMISSION = "Du hast keine Berechtigung eine Lobby zu setzen.";
	
}
