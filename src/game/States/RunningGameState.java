package game.States;

import java.util.UUID;

import context.Context;
import game.Game;
import game.CountDown.CountDown;
import game.CountDown.OnCountDownFinished;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Game.GameCountDownController;
import game.UseCases.RemoveVillagers.RemoveVillagers;
import game.UseCases.RemoveVillagers.RemoveVillagersUseCase;
import main.MainPlugin;
import usecases.TeleportPlayersToLobby.TeleportPlayersToLobbyController;

public class RunningGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown gameCountDown;
	
	private void initializeCountDown(Game game) {
		GameCountDownController controller = new GameCountDownController();
		controller.setOnCountDownFinished(this);
		gameCountDown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, game.getPlayingTimeInSeconds());
		gameCountDown.setCountDownListener(controller);
	}

	private void removeVillagers(String game) {
		RemoveVillagers removeVillagers = new RemoveVillagersUseCase();
		removeVillagers.setGameGateway(Context.gameGateway);
		removeVillagers.execute(game);
	}

	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		if (gameCountDown != null) {
			gameCountDown.resume();
		} else {
//			removeVillagers(game.getName());
			initializeCountDown(game);
			startCountDown();
		}
	}
	
	@Override
	public void leaveGameState(Game game) {
		if (gameCountDown != null && gameCountDown.isFinished()) {
			game.getVillagerSpawner().removeVillager();
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
