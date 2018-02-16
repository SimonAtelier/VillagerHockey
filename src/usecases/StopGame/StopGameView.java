package usecases.StopGame;

import java.util.List;
import java.util.UUID;

public interface StopGameView {

	void displayNoPermission();
	
	void displayNoSuchGame(String game);
	
	void displayAlreadyStopped(String game);
	
	void displaySuccessfullyStopped(String game);
	
	void displayStopping(List<UUID> viewers, String game);
	
}
