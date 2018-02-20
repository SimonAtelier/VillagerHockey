package usecases.teleportplayerstoteamspawns;

import java.util.List;
import java.util.UUID;

import entities.Location;

public interface TeleportPlayersToTeamSpawnsView {

	void displayLocation(List<UUID> viewers, List<Location> locations);
	
}
