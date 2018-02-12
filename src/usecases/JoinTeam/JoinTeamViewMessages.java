package usecases.JoinTeam;

public interface JoinTeamViewMessages {

	static final String JOIN_TEAM_NO_PERMISSION = "Du hast keine Berechtigung einem Team beizutreten.";
	
	static final String JOIN_TEAM_NOW_IN_TEAM = "Du bist jetzt in Team '$team$'.";
	
	static final String JOIN_TEAM_PLAYER_JOIN = "Der Spieler '$player$' ist dem Team '$team$' beigetreten.";
	
	static final String JOIN_TEAM_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";
	
	static final String JOIN_TEAM_NO_SUCH_TEAM = "Ein Team mit dem Namen '$team$' existiert nicht.";
	
	static final String JOIN_TEAM_TEAM_ALREADY_FULL = "Das Team '$team$' hat bereits die maximale Spieleranzahl.";
	
	static final String JOIN_TEAM_ALREADY_JOINED_A_TEAM = "Du bist bereits einem Team beigetreten.";
	
}
