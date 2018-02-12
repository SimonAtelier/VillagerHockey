package usecases.PerformAction.Move;

import java.util.UUID;

import gateways.GameGateway;

public interface Move {

	boolean canMove(UUID player);
	
	void setGameGateway(GameGateway gameGateway);
	
}
