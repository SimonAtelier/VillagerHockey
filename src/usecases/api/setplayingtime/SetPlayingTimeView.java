package usecases.api.setplayingtime;

public interface SetPlayingTimeView {

	void displayNoPermission();
	
	void displayNoSuchGame(String game);
	
	void displayPlayingTimeIsNotAValidNumber(String playingTime);
	
	void displayPlayingTimeSuccessfullySet(String game, String playingTime);
	
}
