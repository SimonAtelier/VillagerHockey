package game.countdown.respawn;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.Game;
import game.countdown.CountDown;
import game.countdown.CountDownListener;
import game.countdown.OnCountDownFinished;

public class RespawnCountDownController implements CountDownListener {

	private RespawnCountDownView view = new RespawnCountDownViewImpl();
	private OnCountDownFinished OnCountDownFinished;
	
	@Override
	public void onUpdateIgnorePaused(CountDown countdown, String game, int timeLeftInSeconds) {
		// Not used
	}

	@Override
	public void onStart(String game, int timeLeftInSeconds) {
		// Not used
	}

	@Override
	public void onStop(String game, int timeLeftInSeconds) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		OnCountDownFinished.onCountDownFinished(gameObject);
		view.displayRespawnTimeOver(getPlayers(game), timeLeftInSeconds);
	}

	@Override
	public void onCountDownOneSecond(CountDown countdown, String game, int timeLeftInSeconds) {
		view.displayRespawnTime(getPlayers(game), timeLeftInSeconds);
	}
	
	private List<UUID> getPlayers(String game) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		return gameObject.getUniquePlayerIds();
	}
	
	public void setOnCountDownFinished(OnCountDownFinished onCountDownFinished) {
		this.OnCountDownFinished = onCountDownFinished;
	}

}
