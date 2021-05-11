package gateways.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import entities.Location;
import gateways.PlayerGateway;
import util.LocationConvert;

public class PlayerGatewayImpl implements PlayerGateway {

	@Override
	public String findPlayerNameById(UUID uniquePlayerId) {
		return Bukkit.getPlayer(uniquePlayerId).getName();
	}

	@Override
	public UUID findPlayerUniqueIdByName(String name) {
		return Bukkit.getPlayer(name).getUniqueId();
	}

	@Override
	public Location findLocationOfPlayer(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		return LocationConvert.toEntityLocation(player.getLocation());
	}

	@Override
	public List<UUID> findAllOnlinePlayers() {
		List<UUID> uniquePlayerIds = new ArrayList<UUID>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			uniquePlayerIds.add(player.getUniqueId());
		}
		return uniquePlayerIds;
	}

	@Override
	public void removeVehicle(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		Entity vehicle = player.getVehicle();
		
		if (vehicle == null)
			return;
		
		vehicle.eject();
		
		List<Entity> passengers = new ArrayList<Entity>(vehicle.getPassengers());
		
		for (Entity passenger : passengers) {
			vehicle.removePassenger(passenger);
		}
			
		
		vehicle.remove();
	}
	
}
