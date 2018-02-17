package usecases.PerformAction.Move;

import java.util.UUID;

import game.Game;
import game.States.RespawnGameState;
import gateways.GameGateway;

public class MoveUseCase implements Move {

	private UUID player;
	private GameGateway gameGateway;
	
	@Override
	public boolean canMove(UUID player) {
		setPlayer(player);
				
		if (playerIsNotIngame())
			return true;
		
		return gameOfPlayerAllowsMove();
	}
	
	private boolean gameOfPlayerAllowsMove() {
		Game game = gameGateway.findGameOfPlayer(player);
		return game.getGameState().getClass() != RespawnGameState.class;
	}

	private boolean playerIsNotIngame() {
		return !gameGateway.isIngame(player);
	}
	
	private void setPlayer(UUID player) {
		this.player = player;
	}
	
	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
