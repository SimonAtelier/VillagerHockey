package game.States;

import java.util.UUID;

import game.Game;
import game.CountDown.CountDown;
import game.CountDown.OnCountDownFinished;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Winner.WinnerCountDownController;
import main.MainPlugin;

public class AnnounceWinnerGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown winnerCountdown;
	
	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		WinnerCountDownController controller = new WinnerCountDownController();
		controller.setOnCountDownFinished(this);
		winnerCountdown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, 5);
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
