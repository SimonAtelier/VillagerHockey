package game;

import java.util.UUID;

import game.States.GameState;

public class GameListenerAdapter implements GameListener, GameStateChangeListener {

	@Override
	public void onGameStateChanged(IGame game, GameState from, GameState to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerJoin(Game game, UUID player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTeamScored(String game, String team) {
		// TODO Auto-generated method stub
		
	}

}
