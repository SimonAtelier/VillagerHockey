package game.CountDown.Winner;

import context.Context;
import game.Game;
import game.CountDown.CountDown;
import game.CountDown.CountDownListener;
import game.States.WaitingGameState;
import usecases.DisplayWinner.DisplayWinnerController;

public class WinnerCountDownController implements CountDownListener {

	@Override
	public void onStart(String game, int timeLeftInSeconds) {
		new DisplayWinnerController().onDisplayWinner(game);
	}

	@Override
	public void onStop(String game, int timeLeftInSeconds) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		gameObject.setGameState(new WaitingGameState());
	}

	@Override
	public void onCountDownOneSecond(CountDown countdown, String game, int timeLeftInSeconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateIgnorePaused(CountDown countdown, String game, int timeLeftInSeconds) {
		if (shouldStopCountDown(game)) {
			countdown.stop();
		}
	}
	
	private boolean shouldStopCountDown(String game) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		return gameObject.getPlayersCount() == 0;
	}

}
