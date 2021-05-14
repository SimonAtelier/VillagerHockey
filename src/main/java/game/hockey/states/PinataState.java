package game.hockey.states;

import game.states.AbstractGameState;
import game.states.GameState;
import usecases.hockey.pinata.PinataController;

public class PinataState extends AbstractGameState {

	private GameState gameState;
	
	public PinataState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void enterGameState() {
		getGame().setGoalsEnabled(false);
		getGame().getVillagerSpawner().spawnPassenger();
		getGame().getVillagerSpawner().setAIEnabled(true);
		new PinataController().onPinata(getGame().getName());
	}
	
	@Override
	public void leaveGameState() {
		getGame().getVillagerSpawner().setAIEnabled(false);
		getGame().setGoalsEnabled(true);
	}
	
	@Override
	public void onTick() {
		
		if (getGame().getVillagerSpawner().isPassengerAlive())
			return;
		
		getGame().getVillagerSpawner().removeVillager();
		getGame().setGoalsEnabled(true);
		transitionToGameState(new RespawnGameState(gameState));
	}
	
	@Override
	public String getName() {
		return "Special Round";
	}
	
}
