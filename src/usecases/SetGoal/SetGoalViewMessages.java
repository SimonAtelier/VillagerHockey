package usecases.SetGoal;

public interface SetGoalViewMessages {

	static final String SET_GOAL_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$name$' existiert nicht.";
	
	static final String SET_GOAL_NO_SUCH_TEAM = "Ein Team mit dem Namen '$name$' existiert nicht.";
	
	static final String SET_GOAL_INVALID_LOCATION_ID = "Die location id '$id$' ist ungültig.";
	
	static final String SET_GOAL_LOCATION_SUCCESSFULLY_SET = "Die location '$id$' wurde erfolgreich gesetzt.";
	
	static final String SET_GOAL_NO_PERMISSION = "Du hast keine Berechtigung eine Tor-Position zu setzen.";
	
	static final String SET_GOAL_NEED_TO_SET_LOCATION_FIRST = "Die Location mit der id '$id$' muss zuerst gesetzt werden.";
	
}
