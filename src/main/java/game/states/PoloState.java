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
	public void enterGameState(Game game) {
		super.enterGameState(game);
		game.setCanLeaveVehicle(false);
		new PoloController().onPolo(game.getName());
	}
	
	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
	}
	
	@Override
	public void onTick(Game game) {
		GoalResponse goalResponse = game.checkGoal();
		
		if (goalResponse == null)
			return;

		game.setCanLeaveVehicle(true);
		game.getVillagerSpawner().removeVillager();
		game.onTeamScored(goalResponse.getTeam(), 1);
		removeVehicles(game);
		transitionToGameState(game, new RespawnGameState(gameState));
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
