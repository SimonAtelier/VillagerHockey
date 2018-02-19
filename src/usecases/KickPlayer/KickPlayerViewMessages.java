package usecases.KickPlayer;

public interface KickPlayerViewMessages {

	static final String KICK_PLAYER_PLAYER_WITH_SUCH_NAME_NOT_FOUND = "Es konnte kein Spieler mit dem Namen '$player$' gefunden werden.";
	
	static final String KICK_PLAYER_PLAYER_IS_NOT_INGAME = "Der Spieler ist keinem Spiel beigetreten.";
	
	static final String KICK_PLAYER_PLAYER_SUCCESSFULLY_KICKED = "Der Spieler wurde erfolgreich aus dem Spiel entfernt.";
	
	static final String KICK_PLAYER_PLAYER_DISPLAY_KICK_MESSAGE = "Du wurdest aus dem Spiel entfernt. Grund: '$reason$'";
	
	static final String KICK_PLAYER_PLAYER_KICKED = "Der Spieler '$player$' wurde aus dem Spiel entfernt.";
	
	static final String KICK_PLAYER_NO_PERMISSION = "Du hast keine Berechtigung einen Spieler zu kicken.";
	
}
