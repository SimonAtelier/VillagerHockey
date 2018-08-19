package gateways;

import java.util.List;
import java.util.UUID;

import entities.Location;

public interface PlayerGateway {

	String findPlayerNameById(UUID uniquePlayerId);
	
	UUID findPlayerUniqueIdByName(String name);
	
	Location findLocationOfPlayer(UUID uniquePlayerId);
	
	List<UUID> findAllOnlinePlayers();
	
}
