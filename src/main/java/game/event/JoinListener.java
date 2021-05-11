package game.event;

import java.util.UUID;

import game.Game;

public interface JoinListener {

	void onPlayerJoin(Game game, UUID player);
	
}
