package game.states;

import game.Game;
import usecases.pinata.PinataController;

public class PinataState extends AbstractGameState {

	private GameState gameState;
	
	public PinataState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void enterGameState(Game game) {
		game.setGoalsEnabled(false);
		game.getVillagerSpawner().spawnPassenger();
		new PinataController().onPinata(game.getName());
	}
	
	@Override
	public void onTick(Game game) {
		
		if (game.getVillagerSpawner().isPassengerAlive())
			return;
		
		game.getVillagerSpawner().removeVillager();
		game.setGoalsEnabled(true);
		transitionToGameState(game, new RespawnGameState(gameState));
	}
	
	@Override
	public String getName() {
		return "Special Round";
	}
	
}
