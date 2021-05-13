package game.states;

import java.util.UUID;

import context.Context;
import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.lobby.LobbyCountDownController;
import usecases.api.saveinventory.SaveInventoryController;
import usecases.api.teleportplayertolobby.TeleportPlayerToLobbyController;
import usecases.encaps.prepareplayerforgame.PreparePlayerForGameController;
import usecases.encaps.prepareplayerforlobby.PreparePlayerForLobbyCommand;

public class WaitingGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown lobbyCountDown;

	@Override
	public void onTick(Game game) {
		lobbyCountDown.tick();
	}

	@Override
	public void enterGameState(Game game) {
		super.enterGameState(game);
		game.getVillagerSpawner().removeVillager();
		int lobbyTimeInSeconds = Context.configuration.getLobbyTime();
		LobbyCountDownController controller = new LobbyCountDownController();
		controller.setOnCountDownFinished(this);
		lobbyCountDown = new SecondsBasedCountDown(game, lobbyTimeInSeconds);
		lobbyCountDown.setCountDownListener(controller);
	}

	@Override
	public void leaveGameState(Game game) {
		super.leaveGameState(game);
		preparePlayersForGame(game);
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(game, new RespawnGameState(new RunningGameState()));
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

	private void preparePlayersForGame(Game game) {
		for (UUID player : game.getUniquePlayerIds()) {
			if (game.getTeams().findTeamOfPlayer(player) == null)
				game.selectLowestTeam(player);
			new PreparePlayerForGameController().onPreparePlayersForGame(game.getName(), player);
		}
	}

	private void preparePlayerForLobby(UUID player) {
		new PreparePlayerForLobbyCommand().execute(player);
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
