package game.States;

import java.util.UUID;

import context.Context;
import game.Game;
import game.CountDown.CountDown;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Lobby.LobbyCountDownController;
import game.UseCases.PreparePlayerForLobby.PreparePlayerForLobby;
import game.UseCases.PreparePlayerForLobby.PreparePlayerForLobbyImpl;
import game.UseCases.TeleportPlayerToLobby.TeleportPlayerToLobby;
import game.UseCases.TeleportPlayerToLobby.TeleportPlayerToLobbyImpl;
import main.MainPlugin;

public class WaitingGameState extends AbstractGameState {

	private CountDown lobbyCountdown;

	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		int lobbyTimeInSeconds = MainPlugin.getInstance().getConfiguration().getLobbyTime();
		lobbyCountdown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, lobbyTimeInSeconds);
		lobbyCountdown.setCountDownListener(new LobbyCountDownController());
	}

	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
	}

	@Override
	public void onPlayerJoin(Game game, UUID player) {
		preparePlayerForLobby(player);
		teleportPlayerToLobby(player);
		if (getPlayersToStart(game) <= 0) {
			lobbyCountdown.start();
		}
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
		if (shouldStopCountDown(game)) {
			lobbyCountdown.stop();
		}
	}

	private void preparePlayerForLobby(UUID player) {
		PreparePlayerForLobby useCase = new PreparePlayerForLobbyImpl();
		useCase.execute(player);
	}

	private void teleportPlayerToLobby(UUID player) {
		TeleportPlayerToLobby useCase = new TeleportPlayerToLobbyImpl();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(player);
	}

	private boolean shouldStopCountDown(Game game) {
		return game.getPlayersCount() < game.getMinimumPlayersToStart();
	}

	private int getPlayersToStart(Game game) {
		return game.getMinimumPlayersToStart() - game.getPlayersCount();
	}

	@Override
	public boolean canPlayerJoin(Game game, UUID player) {
		return !game.isMaximumAmountOfPlayersReached();
	}

	@Override
	public String getName() {
		return "Waiting";
	}

}
