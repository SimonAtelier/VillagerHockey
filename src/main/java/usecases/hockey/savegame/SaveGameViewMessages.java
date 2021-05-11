package usecases.hockey.savegame;

public interface SaveGameViewMessages {

	static final String SAVE_GAME_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";

	static final String SAVE_GAME_SUCCESSFULLY_SAVED_GAME = "Das Spiel wurde erfolgreich gespeichert.";

	static final String SAVE_GAME_ERROR = "Es ist ein Fehler aufgetreten. Das Spiel konnte nicht gespeichert werden.";

	static final String SAVE_GAME_NO_PERMISSION = "Da hast keine Berechtigung ein Spiel zu speichern.";

	static final String SAVE_GAME_CANNOT_SAVE_NO_LOBBY_SET = "Das Spiel konnte nicht gespeichert werden. Es wurde keine 'Lobby' gesetzt.";

	static final String SAVE_GAME_CANNOT_SAVE_NO_VILLAGER_SPAWN_SET = "Das Spiel konnte nicht gespeichert werden. Es wurde kein 'VillagerSpawn' gesetzt.";

	static final String SAVE_GAME_CANNOT_SAVE_PLAYING_TIME_MUST_BE_GREATER_THAN_ZERO = "Das Spiel konnte nicht gespeichert werden. Die Spielzeit muss mehr als 0 Sekunden betragen.";
	
	static final String SAVE_GAME_CANNOT_SAVE_NUMBER_OF_TEAMS_IS_NOT_TWO = "Das Spiel konnte nicht gespeichert werden. Die Anzahl der Teams muss 2 betragen.";
	
	static final String SAVE_GAME_CANNOT_SAVE_NOT_ALL_GOALS_SET = "Das Spiel konnte nicht gespeichert werden. Es ist noch nicht f�r alle Teams ein Tor gesetzt.";
	
	static final String SAVE_GAME_CANNOT_SAVE_SPAWN_LOCATIONS_MISSING = "Das Spiel konnte nicht gespeichert werden. Es sind noch nicht f�r alle Teams 'Spawns' gesetzt.";
	
	static final String SAVE_GAME_CANNOT_SAVE_AMOUNT_OF_TEAM_SPAWNS_IS_NOT_EQUAL = "Das Spiel konnte nicht gespeichert werden. Die Spawnanzahl der einzelnen Teams ist unterschiedlich.";
	
}
