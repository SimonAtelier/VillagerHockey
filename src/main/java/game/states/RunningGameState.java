package game.states;

import java.util.UUID;

import game.Game;
import game.Goal.GoalResponse;
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
	public void onTick(Game game) {
		gameCountDown.tick();
		GoalResponse goalResponse = game.checkGoal();
		if (goalResponse == null)
			return;
		game.getVillagerSpawner().removeVillager();
		game.onTeamScored(goalResponse.getTeam(), 1);
		game.getGameState().transitionToGameState(game, new RespawnGameState(this));
	}

	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		if (gameCountDown != null) {
			gameCountDown.resume();
		} else {
			initializeCountDown(game);
			startCountDown();
		}
	}
	
	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
		if (gameCountDown != null && gameCountDown.isFinished()) {
			new TeleportPlayersToLobbyController().onTeleportPlayersToLobby(game.getName());
		} else if (gameCountDown != null && !gameCountDown.isFinished()) {
			gameCountDown.pause();
		}
	}

	private void startCountDown() {
		gameCountDown.start();
	}

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "Running";
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(game, new AnnounceWinnerGameState());
	}
	
}
