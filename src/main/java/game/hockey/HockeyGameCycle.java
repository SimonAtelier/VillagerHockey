package game.hockey;

import game.GameCycle;
import game.HockeyGame;
import game.Goal.GoalResponse;
import game.states.base.RunningGameState;
import game.states.hockey.RespawnGameState;
import usecases.hockey.updatestatistics.UpdateStatisticsController;

public class HockeyGameCycle implements GameCycle {

	public HockeyGame hockeyGame;
	
	public HockeyGameCycle(HockeyGame hockeyGame) {
		this.hockeyGame = hockeyGame;
	}
	
	@Override
	public void onEnterWaitingGameState() {
		removeVillager();		
	}

	@Override
	public void onEnterAnnounceWinner() {
		updateStatistics();
		removeVillager();
	}
	
	@Override
	public void onTickRunning(RunningGameState runningGameState) {
		GoalResponse goalResponse = hockeyGame.checkGoal();
		if (goalResponse == null)
			return;
		removeVillager();
		hockeyGame.onTeamScored(goalResponse.getTeam(), 1);
		runningGameState.transitionToGameState(new RespawnGameState(runningGameState));
	}

	private void removeVillager() {
		hockeyGame.getVillagerSpawner().removeVillager();
	}
	
	private void updateStatistics() {
		new UpdateStatisticsController().onUpdate(hockeyGame.getName());
	}
	
}
