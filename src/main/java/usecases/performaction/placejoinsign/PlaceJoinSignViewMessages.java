package usecases.performaction.placejoinsign;

public interface PlaceJoinSignViewMessages {

	static final String PLACE_JOIN_SIGN_NO_SUCH_GAME = "Ein Spiel mit dem Namen '$game$' existiert nicht.";
	
	static final String PLACE_JOIN_SIGN_SUCCESS = "JoinSign f�r '$game$' erfolgreich gesetzt. Vergesse nicht das Spiel zu speichern.";
	
	static final String PLACE_JOIN_SIGN_NO_PERMISSION = "Du hast keine Berechtigung ein JoinSign zu setzen.";
	
}
