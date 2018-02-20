package usecases.performaction.openinventory;

import java.util.UUID;

import gateways.GameGateway;

public interface OpenInventory {

	boolean canOpenInventory(UUID uniquePlayerId);
	
	void setGameGateway(GameGateway gameGateway);
	
}
