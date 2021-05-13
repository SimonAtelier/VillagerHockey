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
	public void onTick() {
		lobbyCountDown.tick();
	}

	@Override
	public void enterGameState() {
		getGame().getVillagerSpawner().removeVillager();
		int lobbyTimeInSeconds = Context.configuration.getLobbyTime();
		LobbyCountDownController controller = new LobbyCountDownController();
		controller.setOnCountDownFinished(this);
		lobbyCountDown = new SecondsBasedCountDown(getGame(), lobbyTimeInSeconds);
		lobbyCountDown.setCountDownListener(controller);
	}

	@Override
	public void leaveGameState() {
		preparePlayersForGame(getGame());
	}

	@Override
	public void onCountDownFinished(Game game) {
		transitionToGameState(new RespawnGameState(new RunningGameState()));
	}

	@Override
	public void onPlayerJoin(UUID player) {
		new SaveInventoryController().onSaveInventory(player, true);
		preparePlayerForLobby(player);
		teleportPlayerToLobby(player);
		
		if (shouldStartCountDown()) {
			lobbyCountDown.start();
		}
	}

	@Override
	public void onPlayerLeave(UUID player) {
		if (shouldStopCountDown(getGame())) {
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
	
	private boolean shouldStartCountDown() {
		return getPlayersToStart(getGame()) <= 0;
	}

	private int getPlayersToStart(Game game) {
		return game.getMinimumPlayersToStart() - game.getPlayersCount();
	}

	@Override
	public boolean canPlayerJoin(UUID player) {
		return !getGame().isMaximumAmountOfPlayersReached();
	}

	@Override
	public String getName() {
		return "Waiting";
	}

}
