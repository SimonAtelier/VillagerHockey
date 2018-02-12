package gateways;

import java.util.List;
import java.util.UUID;

import game.Game;
import gateways.impl.GatewayException;

public interface GameGateway {

	boolean addGame(String name);
	
	boolean isIngame(UUID uniquePlayerId);

	boolean containsGame(String name);
	
	List<String> findAllGameNames();
	
	Game findGameByName(String name);
	
	Game getGameOfPlayer(UUID uniquePlayerId);
	
	Game loadGame(String name) throws GatewayException;
	
	void saveGame(String game) throws GatewayException;
	
}
