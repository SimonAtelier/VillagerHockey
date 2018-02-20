package game.states;

import java.util.UUID;

import context.Context;
import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.lobby.LobbyCountDownController;
import game.usecases.prepareplayerforlobby.PreparePlayerForLobby;
import game.usecases.prepareplayerforlobby.PreparePlayerForLobbyUseCase;
import game.usecases.removevillagers.RemoveVillagers;
import game.usecases.removevillagers.RemoveVillagersUseCase;
import game.usecases.teleportplayertolobby.TeleportPlayerToLobbyController;
import usecases.prepareplayerforgame.PreparePlayerForGame;
import usecases.prepareplayerforgame.PreparePlayerForGame.PreparePlayerForGameResponse;
import usecases.prepareplayerforgame.PreparePlayerForGamePresenter;
import usecases.prepareplayerforgame.PreparePlayerForGameUseCase;
import usecases.prepareplayerforgame.PreparePlayerForGameView;
import usecases.prepareplayerforgame.PreparePlayerForGameViewImpl;
import usecases.saveinventory.SaveInventoryController;

public class WaitingGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown lobbyCountDown;

	@Override
	public void onTick(Game game) {
		lobbyCountDown.tick();
	}
	
	@Override
	public void enterGameState(Game game) {
		int lobbyTimeInSeconds = Context.configuration.getLobbyTime();
		LobbyCountDownController controller = new LobbyCountDownController();
		controller.setOnCountDownFinished(this);
		lobbyCountDown = new SecondsBasedCountDown(game, lobbyTimeInSeconds);
		lobbyCountDown.setCountDownListener(controller);
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(game, new RespawnGameState(new RunningGameState()));		
	}

	@Override
	public void leaveGameState(Game game) {
		preparePlayersForGame(game);
		removeVillagers(game.getName());
	}

	@Override
	public void onPlayerJoin(Game game, UUID player) {
		new SaveInventoryController().onSaveInventory(player, true);
		preparePlayerForLobby(player);
		teleportPlayerToLobby(player);
		if (getPlayersToStart(game) <= 0) {
			lobbyCountDown.start();
		}
	}

	@Override
	public void onPlayerLeave(Game game, UUID player) {
		if (shouldStopCountDown(game)) {
			lobbyCountDown.stop();
		}
	}
	
	private void removeVillagers(String game) {
		RemoveVillagers removeVillagers = new RemoveVillagersUseCase();
		removeVillagers.setGameGateway(Context.gameGateway);
		removeVillagers.execute(game);
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
