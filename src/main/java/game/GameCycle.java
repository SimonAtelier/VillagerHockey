package game;

import java.util.UUID;

import game.states.RunningGameState;
import io.github.simonatelier.save.Savable;

public interface GameCycle extends Savable {
	
	void onLoad();
	
	void onUnload();
	
	void onPlayerLeave(UUID player);
	
	void onEnterWaitingGameState();
	
	void onEnterAnnounceWinner();
	
	void onTickRunning(RunningGameState gameState);

}
