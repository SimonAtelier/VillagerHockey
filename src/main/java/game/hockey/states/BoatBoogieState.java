package game.hockey.states;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.Game;
import game.hockey.Goal.GoalResponse;
import game.states.AbstractGameState;
import game.states.GameState;
import usecases.hockey.boatboogie.BoatBoogieController;

public class BoatBoogieState extends AbstractGameState {

	private GameState gameState;
	
	public BoatBoogieState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void enterGameState() {
		getGame().setCanLeaveVehicle(false);
		new BoatBoogieController().onBoatBoogie(getGame().getName());
	}
	
	@Override
	public void leaveGameState() {
		// Do nothing
	}
	
	@Override
	public void onTick() {
		GoalResponse goalResponse = getGame().checkGoal();
		
		if (goalResponse == null)
			return;

		getGame().setCanLeaveVehicle(true);
		getGame().getVillagerSpawner().removeVillager();
		getGame().onTeamScored(goalResponse.getTeam(), 1);
		removeVehicles(getGame());
		transitionToGameState(new RespawnGameState(gameState));
	}
	
	
	private void removeVehicles(Game game) {
		List<UUID> players = game.getUniquePlayerIds();
		
		for (UUID uniquePlayerId : players) {
			Context.playerGateway.removeVehicle(uniquePlayerId);
		}
		
	}
	
	@Override
	public String getName() {
		return "Special Round";
	}

}
