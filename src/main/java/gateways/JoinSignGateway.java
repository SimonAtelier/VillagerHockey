package gateways;

import java.util.List;

import entities.Location;

public interface JoinSignGateway {

	List<Location> findJoinSignLocations(String game);
	
	void addLocation(String game, Location location);
	
	void removeLocation(String game, Location location);
	
}
