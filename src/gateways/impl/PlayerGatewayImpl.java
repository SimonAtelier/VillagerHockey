package gateways.impl;

import java.util.UUID;

import org.bukkit.Bukkit;
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

}
