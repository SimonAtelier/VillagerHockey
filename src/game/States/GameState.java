package game.States;

import java.util.UUID;

import game.Game;

public interface GameState {
	
	void onEnterRespawnPhase(Game game);
	
	void onLeaveRespawnPhase(Game game);
	
	void onEnterGameState(Game game);
	
	void onLeaveGameState(Game game);
	
	void onPlayerJoin(Game game, UUID player);
	
	void onPlayerLeave(Game game, UUID player);
	
	boolean canPlayerJoin(Game game, UUID player);
	
	String getName();
	
}
