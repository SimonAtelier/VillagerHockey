package game.States;

import java.util.UUID;

import context.Context;
import game.Game;
import game.CountDown.CountDown;
import game.CountDown.OnCountDownFinished;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Game.GameCountDownController;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGame;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGame.PreparePlayerForGameResponse;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameUseCase;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGamePresenter;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameView;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameViewImpl;
import game.UseCases.RemoveVillagers.RemoveVillagers;
import game.UseCases.RemoveVillagers.RemoveVillagersUseCase;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobbyController;
import main.MainPlugin;

public class RunningGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown gameCountDown;
	
	private void initializeCountDown(Game game) {
		GameCountDownController controller = new GameCountDownController();
		controller.setOnCountDownFinished(this);
		gameCountDown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, game.getPlayingTimeInSeconds());
		gameCountDown.setCountDownListener(controller);
	}

	private void preparePlayersForGame(Game game) {
		PreparePlayerForGameView view = new PreparePlayerForGameViewImpl();
		PreparePlayerForGameResponse presenter = new PreparePlayerForGamePresenter(view);
		PreparePlayerForGame useCase = new PreparePlayerForGameUseCase();
		
		for (UUID player : game.getUniquePlayerIds()) {
			useCase.execute(player, presenter);
			if (game.getTeams().findTeamOfPlayer(player) == null) {
				game.selectLowestTeam(player);
			}
		}
	}

	private void removeVillagers(String game) {
		RemoveVillagers removeVillagers = new RemoveVillagersUseCase();
		removeVillagers.setGameGateway(Context.gameGateway);
		removeVillagers.execute(game);
	}

	@Override
	public void onEnterRespawnPhase(Game game) {
		gameCountDown.pause();
	}

	@Override
	public void onLeaveRespawnPhase(Game game) {
		gameCountDown.resume();
	}

	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		removeVillagers(game.getName());
		preparePlayersForGame(game);
		initializeCountDown(game);
		startCountDown();
	}
	
	@Override
	public void leaveGameState(Game game) {
		new TeleportPlayersToLobbyController().onTeleportPlayersToLobby(game.getName());
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
