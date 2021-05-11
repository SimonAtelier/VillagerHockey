package gateways;

import entities.Location;

public interface TeamSpawnsGateway {

	void addTeamSpawn(String game, String team, Location location);
	
}
