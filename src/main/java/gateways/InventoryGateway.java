package gateways;

import java.util.UUID;

import gateways.impl.GatewayException;

public interface InventoryGateway {
	
	void saveInventoryOfPlayer(UUID uniquePlayerId) throws GatewayException;
	
	void loadInventoryOfPlayer(UUID uniquePlayerId) throws GatewayException;
	
	void clearInventoryOfPlayer(UUID uniquePlayerId);

}
