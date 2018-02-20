package usecases.joingame;

public interface JoinGameViewMessages {

	static final String JOIN_GAME_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$name$' existiert nicht.";
	
	static final String JOIN_GAME_PLAYER_JOIN = "Der Spieler '$player$' hat das Spiel betreten.";
	
	static final String JOIN_GAME_PLAYER_ALREADY_JOINED_A_GAME = "Du bist bereits einem Spiel beigetreten.";
	
	static final String JOIN_GAME_PLAYER_CANNOT_JOIN = "Du kannst dem Spiel nicht beitreten.";
	
	static final String JOIN_GAME_NO_PERMISSION = "Du hast keine Berechtigung dem Spiel beizutreten.";
	
	static final String JOIN_GAME_MAXIMUM_AMOUNT_OF_PLAYERS_REACHED = "Du kannst dem Spiel nicht beitreten. Die maximale Spieleranzahl ist bereits erreicht.";
	
}
