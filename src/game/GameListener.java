package game;

import java.util.UUID;

import game.States.GameState;

public interface GameListener {

//	void onGameStateChanged(Game game, GameState from, GameState to);

	void onPlayerJoin(Game game, UUID player);
	
	void onPlayerLeave(Game game, UUID player);
	
	void onTeamScored(String game, String team);
	
}
