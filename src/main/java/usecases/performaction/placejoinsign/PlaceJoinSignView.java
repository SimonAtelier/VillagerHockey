package usecases.performaction.placejoinsign;

public interface PlaceJoinSignView {

	void displayJoinSignSuccessfullySet(String game, ResponseModel responseModel);
	
	void displayNoPermission();
	
	void displayNoSuchGame(String game);
	
}
