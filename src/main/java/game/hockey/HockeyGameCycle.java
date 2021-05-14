package game.hockey;

import game.GameCycle;
import game.hockey.Goal.GoalResponse;
import game.hockey.states.RespawnGameState;
import game.states.RunningGameState;
import usecases.encaps.displaywinner.DisplayWinnerController;
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
		displayWinner();
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
	
	private void displayWinner() {
		new DisplayWinnerController().onDisplayWinner(hockeyGame.getName());
	}

	private void removeVillager() {
		hockeyGame.getVillagerSpawner().removeVillager();
	}
	
	private void updateStatistics() {
		new UpdateStatisticsController().onUpdate(hockeyGame.getName());
	}
	
}
