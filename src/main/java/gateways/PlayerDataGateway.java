package gateways;

import java.util.UUID;

import gateways.impl.GatewayException;

public interface PlayerDataGateway {

	void save(UUID uniquePlayerId) throws GatewayException;
	
	void load(UUID uniquePlayerId) throws GatewayException;
	
}
