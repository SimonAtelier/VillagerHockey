package game.States;

import java.util.UUID;

import context.Context;
import game.Game;
import game.CountDown.CountDown;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Game.GameCountDownController;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGame;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGame.PreparePlayerForGameResponse;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameImpl;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGamePresenter;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameView;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameViewImpl;
import game.UseCases.RemoveVillagers.RemoveVillagers;
import game.UseCases.RemoveVillagers.RemoveVillagersImpl;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobby;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobby.TeleportPlayersToLobbyResponse;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobbyPresenter;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobbyUseCase;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobbyView;
import game.UseCases.TeleportPlayersToLobby.TeleportPlayersToLobbyViewImpl;
import main.MainPlugin;

public class RunningGameState extends AbstractGameState {

	private CountDown gameCountDown;

	private void preparePlayersForGame(Game game) {
		PreparePlayerForGameView view = new PreparePlayerForGameViewImpl();
		PreparePlayerForGameResponse presenter = new PreparePlayerForGamePresenter(view);
		PreparePlayerForGame useCase = new PreparePlayerForGameImpl();
		
		for (UUID player : game.getUniquePlayerIds()) {
			useCase.execute(player, presenter);
			if (game.getTeams().findTeamOfPlayer(player) == null) {
				game.selectLowestTeam(player);
			}
		}
	}
	
	private void teleportPlayersToLobby(Game game) {
		TeleportPlayersToLobbyView view = new TeleportPlayersToLobbyViewImpl();
		TeleportPlayersToLobbyResponse presenter = new TeleportPlayersToLobbyPresenter(view);
		TeleportPlayersToLobby useCase = new TeleportPlayersToLobbyUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(game.getName(), presenter);
	}

	private void removeVillagers(String game) {
		RemoveVillagers removeVillagers = new RemoveVillagersImpl();
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
		teleportPlayersToLobby(game);
		game.setGameState(new AnnounceWinnerGameState());
	}

	private void startCountDown() {
		gameCountDown.start();
	}

	private void initializeCountDown(Game game) {
		gameCountDown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, game.getPlayingTimeInSeconds());
		gameCountDown.setCountDownListener(new GameCountDownController());
	}

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return false;
	}

	@Override
	public String getName() {
		return "Running";
	}

}
