package usecases.teleportplayerstolobby;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import entities.Location;
import util.LocationConvert;

public class TeleportPlayersToLobbyViewImpl implements TeleportPlayersToLobbyView {

	@Override
	public void displayLocation(List<UUID> viewers, Location location) {
		for (UUID viewer : viewers) {
			displayLocation(viewer, location);
		}
	}

	private void displayLocation(UUID viewer, Location location) {
		Player player = Bukkit.getPlayer(viewer);
		player.teleport(LocationConvert.toBukkitLocation(location));
	}
	
}
