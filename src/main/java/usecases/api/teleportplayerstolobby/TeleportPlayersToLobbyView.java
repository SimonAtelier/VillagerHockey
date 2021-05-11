package usecases.api.teleportplayerstolobby;

import java.util.List;
import java.util.UUID;

import entities.Location;

public interface TeleportPlayersToLobbyView {

	void displayLocation(List<UUID> viewers, Location location);
	
}
