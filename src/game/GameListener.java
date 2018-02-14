package game;

import java.util.UUID;

public interface GameListener {

	void onPlayerJoin(Game game, UUID player);
	
	void onPlayerLeave(Game game, UUID player);
	
	void onTeamScored(String game, String team);
	
}
