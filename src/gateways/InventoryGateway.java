package gateways;

import java.util.UUID;

import gateways.impl.GatewayException;

public interface InventoryGateway {
	
	void save(UUID uniquePlayerId) throws GatewayException;
	
	void load(UUID uniquePlayerId) throws GatewayException;
	
	void clearInventoryOfPlayer(UUID uniquePlayerId);

}
