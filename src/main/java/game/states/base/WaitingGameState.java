package game.states.base;

import java.util.UUID;

import context.Context;
import game.Game;
import game.countdown.CountDown;
import game.countdown.OnCountDownFinished;
import game.countdown.SecondsBasedCountDown;
import game.countdown.lobby.LobbyCountDownController;
import game.states.hockey.RespawnGameState;
import usecases.api.saveinventory.SaveInventoryController;
import usecases.api.teleportplayertolobby.TeleportPlayerToLobbyController;
import usecases.encaps.prepareplayerforgame.PreparePlayerForGameController;
import usecases.encaps.prepareplayerforlobby.PreparePlayerForLobbyCommand;

public class WaitingGameState extends AbstractGameState implements OnCountDownFinished {

	private CountDown lobbyCountDown;

	@Override
	public void onTick() {
		tickCountDown();
	}

	@Override
	public void enterGameState() {
		getGame().getGameCycle().onEnterWaitingGameState(); 
		initializeCountDown();
	}
	
	private void initializeCountDown() {
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
		saveInventory(player);
		preparePlayerForLobby(player);
		teleportPlayerToLobby(player);
		
		if (shouldStartCountDown())
			startCountDown();
	}

	@Override
	public void onPlayerLeave(UUID player) {
		if (shouldStopCountDown())
			stopCountDown();
	}
	
	private void startCountDown() {
		getLobbyCountDown().start();
	}
	
	private void stopCountDown() {
		getLobbyCountDown().stop();
	}
	
	private void saveInventory(UUID player) {
		new SaveInventoryController().onSaveInventory(player, true);
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
	
	private void tickCountDown() {
		getLobbyCountDown().tick();
	}

	private boolean shouldStopCountDown() {
		return getGame().getPlayersCount() < getGame().getMinimumPlayersToStart();
	}
	
	private boolean shouldStartCountDown() {
		return getPlayersToStart() <= 0;
	}

	private int getPlayersToStart() {
		return getGame().getMinimumPlayersToStart() - getGame().getPlayersCount();
	}

	@Override
	public boolean canPlayerJoin(UUID player) {
		return !getGame().isMaximumAmountOfPlayersReached();
	}
	
	private CountDown getLobbyCountDown() {
		return lobbyCountDown;
	}

	@Override
	public String getName() {
		return "Waiting";
	}

}
