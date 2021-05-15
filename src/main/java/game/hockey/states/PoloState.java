package game.hockey.states;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.Game;
import game.hockey.HockeyGameCycle;
import game.hockey.Goal.GoalResponse;
import game.states.AbstractGameState;
import game.states.GameState;
import usecases.hockey.polo.PoloController;

public class PoloState extends AbstractGameState {

	private GameState gameState;
	
	public PoloState(GameState gameState) {
		this.gameState = gameState;
	}
	
	private HockeyGameCycle getGameCycle() {
		return (HockeyGameCycle) getGame().getGameCycle();
	}
	
	@Override
	public void enterGameState() {
		getGameCycle().setCanLeaveVehicle(false);
		new PoloController().onPolo(getGame().getName());
	}
	
	@Override
	public void leaveGameState() {
		// Do nothing
	}
	
	@Override
	public void onTick() {
		GoalResponse goalResponse = getGameCycle().checkGoal();
		
		if (goalResponse == null)
			return;

		getGameCycle().setCanLeaveVehicle(true);
		getGameCycle().removeVillager();
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
