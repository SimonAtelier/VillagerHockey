package game.Event;

import java.util.UUID;

import game.Game;

public interface GameListener {

	void onPlayerJoin(Game game, UUID player);
	
	void onPlayerLeave(Game game, UUID player);
	
	void onTeamScored(String game, String team);
	
}
