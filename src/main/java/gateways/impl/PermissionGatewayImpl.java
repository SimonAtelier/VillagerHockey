package gateways.impl;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import gateways.PermissionGateway;

public class PermissionGatewayImpl implements PermissionGateway {

	@Override
	public boolean hasPermission(UUID uniquePlayerId, String permission) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		return player.hasPermission(permission);
	}

}
