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
	public void onTick(Game game) {
		winnerCountdown.tick();
	}
	
	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		WinnerCountDownController controller = new WinnerCountDownController();
		controller.setOnCountDownFinished(this);
		winnerCountdown = new SecondsBasedCountDown(game, 5);
		winnerCountdown.setCountDownListener(controller);
		winnerCountdown.start();
	}

	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
		game.leaveAll();
		game.getTeams().resetTeamScores();
	}

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "AnnounceWinner";
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(game, new WaitingGameState());	
	}

}
