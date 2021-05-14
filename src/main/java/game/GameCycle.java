package game;

import game.states.RunningGameState;

public interface GameCycle {
	
	void onEnterWaitingGameState();
	
	void onEnterAnnounceWinner();
	
	void onTickRunning(RunningGameState gameState);

}
