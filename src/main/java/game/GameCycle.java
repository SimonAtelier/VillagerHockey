package game;

import game.states.RunningGameState;

public interface GameCycle {
	
	void onLoad();
	
	void onUnload();
	
	void onEnterWaitingGameState();
	
	void onEnterAnnounceWinner();
	
	void onTickRunning(RunningGameState gameState);

}
