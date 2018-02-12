package game.CountDown.Game;

import java.util.List;
import java.util.UUID;

public interface GameCountDownView {

	void displayPlayingTimeLeft(List<UUID> viewers, String timeLeft);
	
	void displayMinutesTillEnd(List<UUID> viewers, int minutes);
	
}
