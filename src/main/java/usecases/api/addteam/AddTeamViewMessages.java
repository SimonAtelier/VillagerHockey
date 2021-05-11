package usecases.api.addteam;

public interface AddTeamViewMessages {
	
	static final String ADD_TEAM_NO_PERMISSION = "Du hast keine Berechtigung ein Team hinzuzuf�gen.";
	
	static final String ADD_TEAM_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";
	
	static final String ADD_TEAM_NAME_ALREADY_EXISTS = "Ein Team mit dem Namen '$team$' existiert bereits.";
	
	static final String ADD_TEAM_COLOR_ALREADY_EXITS = "Ein Team mit der Farbe '$color$' existiert bereits.";
	
	static final String ADD_TEAM_NAME_IS_INVALID = "Der Teamname '$team$' ist ung�ltig.";
	
	static final String ADD_TEAM_SUCCESS = "Das Team '$team$' wurde dem Spiel '$game$' erfolgreich hinzugef�gt.";
	
	static final String ADD_TEAM_COLOR_IS_NOT_VALID = "Die Farbe '$color$' ist ung�ltig. M�gliche Werte: $values$";

	static final String ADD_TEAM_MAXIMUM_AMOUNT_ALREADY_REACHED = "Die maximale Teamanzahl von '$maximum$' ist bereits erreicht.";
	
}
