package usecases.encaps.teleportplayerstoteamspawns;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import entities.Location;
import util.LocationConvert;

public class TeleportPlayersToTeamSpawnsViewImpl implements TeleportPlayersToTeamSpawnsView {

	@Override
	public void displayLocation(List<UUID> viewers, List<Location> locations) {
		for (int i = 0; i < viewers.size(); i++) {
			UUID viewer = viewers.get(i);
			Location location = locations.get(i);
			displayLocation(viewer, location);
		}
	}

	private void displayLocation(UUID viewer, Location location) {
		Player player = Bukkit.getPlayer(viewer);
		player.teleport(LocationConvert.toBukkitLocation(location));
	}

}
