package gateways;

import java.util.List;
import java.util.UUID;

import game.Game;
import gateways.impl.GatewayException;

public interface GameGateway {

	void addGame(String name);
	
	boolean isIngame(UUID uniquePlayerId);

	boolean containsGame(String name);
	
	List<String> findAllGameNames();
	
	Game findGameByName(String name);
	
	Game findGameOfPlayer(UUID uniquePlayerId);
	
	Game loadGame(String name) throws GatewayException;
	
	void saveGame(String game) throws GatewayException;
	
	void deleteGame(String game);
	
	void loadGames();
	
	void unloadGames();
	
}
