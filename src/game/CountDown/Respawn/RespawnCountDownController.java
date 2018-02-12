package game.CountDown.Respawn;

import java.util.List;
import java.util.UUID;

import context.Context;
import game.Game;
import game.CountDown.CountDown;
import game.CountDown.CountDownListener;

public class RespawnCountDownController implements CountDownListener {

	private RespawnCountDownView view = new RespawnCountDownViewImpl();
	
	@Override
	public void onUpdateIgnorePaused(CountDown countdown, String game, int timeLeftInSeconds) {
	}

	@Override
	public void onStart(String game, int timeLeftInSeconds) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		gameObject.enterRespawnPhase();
	}

	@Override
	public void onStop(String game, int timeLeftInSeconds) {
		Game gameObject = Context.gameGateway.findGameByName(game);
		gameObject.leaveRespawnPhase();
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

}
