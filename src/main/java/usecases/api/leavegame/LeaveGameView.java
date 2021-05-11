package usecases.api.leavegame;

import java.util.List;
import java.util.UUID;

public interface LeaveGameView {

	void displayPlayerIsNotIngame();
	
	void displayPlayerLeave(List<UUID> viewers, String player);
	
}
