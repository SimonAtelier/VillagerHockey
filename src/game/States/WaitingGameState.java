package game.States;

import java.util.UUID;

import game.Game;
import game.CountDown.CountDown;
import game.CountDown.OnCountDownFinished;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Lobby.LobbyCountDownController;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGame;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGamePresenter;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameUseCase;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameView;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGameViewImpl;
import game.UseCases.PreparePlayerForGame.PreparePlayerForGame.PreparePlayerForGameResponse;
import game.UseCases.PreparePlayerForLobby.PreparePlayerForLobby;
import game.UseCases.PreparePlayerForLobby.PreparePlayerForLobbyUseCase;
import game.UseCases.TeleportPlayerToLobby.TeleportPlayerToLobbyController;
import main.MainPlugin;

public class WaitingGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown lobbyCountdown;

	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		int lobbyTimeInSeconds = MainPlugin.getInstance().getConfiguration().getLobbyTime();
		LobbyCountDownController controller = new LobbyCountDownController();
		controller.setOnCountDownFinished(this);
		lobbyCountdown = new SecondsBasedCountDown(MainPlugin.getInstance(), game, lobbyTimeInSeconds);
		lobbyCountdown.setCountDownListener(controller);
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(game, new RespawnGameState(new RunningGameState()));		
	}

	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
		preparePlayersForGame(game);
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

	private void preparePlayerForLobby(UUID player) {
		PreparePlayerForLobby useCase = new PreparePlayerForLobbyUseCase();
		useCase.execute(player);
	}

	private void teleportPlayerToLobby(UUID player) {
		new TeleportPlayerToLobbyController().onTeleportPlayerToLobby(player);
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
