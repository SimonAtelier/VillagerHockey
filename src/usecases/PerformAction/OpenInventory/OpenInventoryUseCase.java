package usecases.PerformAction.OpenInventory;

import java.util.UUID;

import context.Context;
import game.Game;
import game.States.WaitingGameState;
import gateways.GameGateway;

public class OpenInventoryUseCase implements OpenInventory {

	@Override
	public boolean canOpenInventory(UUID uniquePlayerId) {
		if (!Context.gameGateway.isIngame(uniquePlayerId)) {
			return true;
		}
		
		GameGateway gameGateway = Context.gameGateway;
		Game game = gameGateway.getGameOfPlayer(uniquePlayerId);
		return game.getGameState().getClass() == WaitingGameState.class;
	}

}
