package game.countdown.lobby;

import java.util.List;
import java.util.UUID;

public interface LobbyCountDownView {

	void displayGameStartsInGivenSeconds(List<UUID> viewers, int seconds);
	
	void displayCountDownTimeInSeconds(List<UUID> viewers, int seconds);
	
	void displayGameStarts(List<UUID> viewers);
	
	void displayMapTitle(List<UUID> viewers, String title, String subtitle, int time);
	
}
