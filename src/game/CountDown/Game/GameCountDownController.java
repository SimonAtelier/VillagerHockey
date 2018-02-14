package game.CountDown.Game;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.CountDown.CountDownListener;
import game.CountDown.OnCountDownFinished;
import game.Game;
import game.CountDown.CountDown;

public class GameCountDownController implements CountDownListener {

	private OnCountDownFinished onCountDownFinished;
	private GameCountDownView view = new GameCountDownViewImpl();

	@Override
	public void onUpdateIgnorePaused(CountDown countdown, String game, int timeLeftInSeconds) {
		if (shouldStopCountDown(game)) {
			countdown.stop();
		}		
	}

	@Override
	public void onStart(String game, int timeLeftInSeconds) {
		displayPlayingTimeLeft(game, timeLeftInSeconds);
	}

	@Override
	public void onStop(String game, int timeLeftInSeconds) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		gameObject.onGameCountDownFinished();
		onCountDownFinished.onCountDownFinished(gameObject);
	}

	@Override
	public void onCountDownOneSecond(CountDown countdown, String game, int timeLeftInSeconds) {
		if (timeLeftInSeconds % 60 == 0) {
			view.displayMinutesTillEnd(getPlayers(game), timeLeftInSeconds / 60);
		}
		
		displayPlayingTimeLeft(game, timeLeftInSeconds);
	}
	
	private boolean shouldStopCountDown(String game) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		return gameObject.getPlayersCount() == 0;
	}

	private void displayPlayingTimeLeft(String game, int timeLeftInSeconds) {
		view.displayPlayingTimeLeft(getPlayers(game), formatPlayingTimeLeft(timeLeftInSeconds));
	}

	private String formatPlayingTimeLeft(int playingTimeLeftInSeconds) {
		return playingTimeLeftInSeconds / 60 + ":" + (playingTimeLeftInSeconds % 60 < 10 ? "0" : "")
				+ playingTimeLeftInSeconds % 60;
	}

	private List<UUID> getPlayers(String gameName) {
		Game game = Context.gameGateway.findGameByName(gameName);
		return game.getUniquePlayerIds();
	}
	
	public void setOnCountDownFinished(OnCountDownFinished onCountDownFinished) {
		this.onCountDownFinished = onCountDownFinished;
	}

}
