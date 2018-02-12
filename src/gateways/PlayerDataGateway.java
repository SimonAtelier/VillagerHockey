package gateways;

import java.util.UUID;

import gateways.impl.GatewayException;

public interface PlayerDataGateway {

	public void save(UUID uniquePlayerId) throws GatewayException;
	
	public void load(UUID uniquePlayerId) throws GatewayException;
	
}
