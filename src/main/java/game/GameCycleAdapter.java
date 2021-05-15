package game;

import java.io.IOException;
import java.util.UUID;

import game.states.RunningGameState;
import io.github.simonatelier.save.Exporter;
import io.github.simonatelier.save.Importer;

public class GameCycleAdapter implements GameCycle {

	@Override
	public void onEnterWaitingGameState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnterAnnounceWinner() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTickRunning(RunningGameState gameState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnload() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onPlayerLeave(UUID player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Exporter exporter) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(Importer importer) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
