package game.states;

import java.util.UUID;

import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.game.GameCountDownController;
import usecases.api.teleportplayerstolobby.TeleportPlayersToLobbyController;

public class RunningGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown gameCountDown;

	private void initializeCountDown(Game game) {
		GameCountDownController controller = new GameCountDownController();
		controller.setOnCountDownFinished(this);
		gameCountDown = new SecondsBasedCountDown(game, game.getPlayingTimeInSeconds());
		gameCountDown.setCountDownListener(controller);
	}

	@Override
	public void onTick() {
		gameCountDown.tick();
		getGame().getGameCycle().onTickRunning(this);
	}

	@Override
	public void enterGameState() {
		if (gameCountDown != null) {
			gameCountDown.resume();
		} else {
			initializeCountDown(getGame());
			startCountDown();
		}
	}

	@Override
	public void leaveGameState() {
		if (gameCountDown != null && gameCountDown.isFinished()) {
			new TeleportPlayersToLobbyController().onTeleportPlayersToLobby(getGame().getName());
		} else if (gameCountDown != null && !gameCountDown.isFinished()) {
			gameCountDown.pause();
		}
	}

	private void startCountDown() {
		gameCountDown.start();
	}

	@Override
	public boolean canPlayerJoin(UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "Running";
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(new AnnounceWinnerGameState());
	}

}
