package usecases.addteamspawn;

public interface AddTeamSpawnViewMessages {

	static final String ADD_TEAM_SPAWN_NO_SUCH_GAME = "Es existiert kein Spiel mit diesem Namen.";
	
	static final String ADD_TEAM_SPAWN_NO_SUCH_TEAM = "Es existiert kein Team mit diesem Namen.";
	
	static final String ADD_TEAM_SPAWN_SUCCESSFULLY_ADD = "Teamspawn für Team '$team$' erfolgreich hinzugefügt.";
	
	static final String ADD_TEAM_SPAWN_NO_PERMISSION = "Du hast keine Berechtigung einen Teamspawn hinzuzufügen.";
	
}
