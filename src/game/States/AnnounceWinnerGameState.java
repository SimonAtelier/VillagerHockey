package game.States;

import java.util.UUID;

import game.Game;
import game.CountDown.CountDown;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Winner.WinnerCountDownController;
import main.MainPlugin;

public class AnnounceWinnerGameState extends AbstractGameState {

	private CountDown winnerCountdown;
	
	@Override
	public void onEnterGameState(Game game) {
		super.onEnterGameState(game);
		winnerCountdown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, 5);
		winnerCountdown.setCountDownListener(new WinnerCountDownController());
		winnerCountdown.start();
	}

	@Override
	public void onLeaveGameState(Game game) {
		super.onLeaveGameState(game);
		game.removePlayers();
		game.resetTeamScores();
	}

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "AnnounceWinner";
	}

}
