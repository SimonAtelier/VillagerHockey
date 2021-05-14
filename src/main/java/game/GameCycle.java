package game;

import game.states.base.RunningGameState;

public interface GameCycle {
	
	void onEnterWaitingGameState();
	
	void onEnterAnnounceWinner();
	
	void onTickRunning(RunningGameState gameState);

}
