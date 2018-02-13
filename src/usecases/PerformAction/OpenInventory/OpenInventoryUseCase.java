package usecases.PerformAction.OpenInventory;

import java.util.UUID;

import game.Game;
import game.States.WaitingGameState;
import gateways.GameGateway;

public class OpenInventoryUseCase implements OpenInventory {

	private UUID uniquePlayerId;
	private Game game;
	private GameGateway gameGateway;
	
	@Override
	public boolean canOpenInventory(UUID uniquePlayerId) {
		setPlayer(uniquePlayerId);
		
		if (playerIsNotIngame()) {
			return true;
		}
		
		findGameOfPlayer();
		
		return gameStateIsWaiting();
	}
	
	private void findGameOfPlayer() {
		game = gameGateway.getGameOfPlayer(uniquePlayerId);
	}
	
	private boolean playerIsNotIngame() {
		return !gameGateway.isIngame(uniquePlayerId);
	}
	
	private boolean gameStateIsWaiting() {
		return game.getGameState().getClass() == WaitingGameState.class;
	}
	
	private void setPlayer(UUID uniquePlayerId) {
		this.uniquePlayerId = uniquePlayerId;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
