package game.countdown.winner;

import context.Context;
import game.Game;
import game.countdown.CountDown;
import game.countdown.CountDownListener;
import game.countdown.OnCountDownFinished;
import usecases.encaps.displaywinner.DisplayWinnerController;
import usecases.hockey.updatestatistics.UpdateStatisticsController;

public class WinnerCountDownController implements CountDownListener {

	private OnCountDownFinished onCountDownFinished;
	
	@Override
	public void onStart(String game, int timeLeftInSeconds) {
		updateStatistics(game);
		new DisplayWinnerController().onDisplayWinner(game);
	}
	
	private void updateStatistics(String game) {
		new UpdateStatisticsController().onUpdate(game);
	}

	@Override
	public void onStop(String game, int timeLeftInSeconds) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		onCountDownFinished.onCountDownFinished(gameObject);
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
	
	public void setOnCountDownFinished(OnCountDownFinished onCountDownFinished) {
		this.onCountDownFinished = onCountDownFinished;
	}

}
