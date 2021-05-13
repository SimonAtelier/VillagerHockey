package game.states;

import java.util.UUID;

import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.winner.WinnerCountDownController;

public class AnnounceWinnerGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown winnerCountdown;
	
	@Override
	public void onTick() {
		winnerCountdown.tick();
	}
	
	@Override
	public void enterGameState() {
		getGame().getVillagerSpawner().removeVillager();
		initializeCountDown();
	}
	
	private void initializeCountDown() {
		WinnerCountDownController controller = new WinnerCountDownController();
		controller.setOnCountDownFinished(this);
		winnerCountdown = new SecondsBasedCountDown(getGame(), 5);
		winnerCountdown.setCountDownListener(controller);
		winnerCountdown.start();
	}

	@Override
	public void leaveGameState() {
		getGame().leaveAll();
		getGame().getTeams().resetTeamScores();
	}

	@Override
	public boolean canPlayerJoin(UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "AnnounceWinner";
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(new WaitingGameState());	
	}

}
