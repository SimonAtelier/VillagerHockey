package game.hockey.states;

import game.hockey.HockeyGameCycle;
import game.hockey.VillagerSpawner;
import game.states.AbstractGameState;
import game.states.GameState;
import usecases.hockey.pinata.PinataController;

public class PinataState extends AbstractGameState {

	private GameState gameState;
	
	public PinataState(GameState gameState) {
		this.gameState = gameState;
	}
	
	private HockeyGameCycle getGameCycle() {
		return (HockeyGameCycle) getGame().getGameCycle();
	}
	
	private VillagerSpawner getVillagerSpawner() {
		return getGameCycle().getVillagerSpawner();
	}
	
	@Override
	public void enterGameState() {
		getGameCycle().setGoalsEnabled(false);
		getVillagerSpawner().spawnPassenger();
		getVillagerSpawner().setAIEnabled(true);
		new PinataController().onPinata(getGame().getName());
	}
	
	@Override
	public void leaveGameState() {
		getVillagerSpawner().setAIEnabled(false);
		getGameCycle().setGoalsEnabled(true);
	}
	
	@Override
	public void onTick() {
		
		if (getGameCycle().getVillagerSpawner().isPassengerAlive())
			return;
		
		getGameCycle().removeVillager();
		getGameCycle().setGoalsEnabled(true);
		transitionToGameState(new RespawnGameState(gameState));
	}
	
	@Override
	public String getName() {
		return "Special Round";
	}
	
}
