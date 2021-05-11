package game.event;

import java.util.UUID;

import game.Game;

public interface LeaveListener {

	void onPlayerLeave(Game game, UUID player);
	
}
