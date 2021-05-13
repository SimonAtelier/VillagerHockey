package game.states;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.Game;
import game.Goal.GoalResponse;
import usecases.hockey.polo.PoloController;

public class PoloState extends AbstractGameState {

	private GameState gameState;
	
	public PoloState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void enterGameState() {
		getGame().setCanLeaveVehicle(false);
		new PoloController().onPolo(getGame().getName());
	}
	
	@Override
	public void leaveGameState() {
		// Empty
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
