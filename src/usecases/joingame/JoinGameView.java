package usecases.joingame;

import java.util.List;
import java.util.UUID;

public interface JoinGameView {

	void displayNoSuchGame(String name);
	
	void displayPlayerAlreadyJoinedAGame();
	
	void displayCannotJoin();
	
	void displayMaximumAmountOfPlayersReached();
	
	void displayNoPermission();
	
	void displayPlayerJoin(List<UUID> viewers, String player);
	
}
