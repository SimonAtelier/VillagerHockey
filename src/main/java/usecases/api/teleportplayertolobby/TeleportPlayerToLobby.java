package usecases.api.teleportplayertolobby;

import java.util.UUID;

import gateways.GameGateway;

public interface TeleportPlayerToLobby {

	void execute(UUID uniquePlayerId);
	
	void setGameGateway(GameGateway gameGateway);
	
}
