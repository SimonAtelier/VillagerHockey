package game.states;

import game.Game;
import game.Goal.GoalResponse;
import usecases.pinata.PinataController;

public class PinataState extends AbstractGameState {

	private GameState gameState;
	
	public PinataState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void enterGameState(Game game) {
		game.setGoalsEnabled(false);
		new PinataController().onPinata(game.getName());
	}
	
	@Override
	public void onTick(Game game) {
		GoalResponse goalResponse = game.checkGoal();
		
		if (goalResponse == null)
			return;
		
		game.getVillagerSpawner().removeVillager();
		game.setGoalsEnabled(true);
		transitionToGameState(game, gameState);
	}
	
	@Override
	public String getName() {
		return "Special Round";
	}
	
}
