package util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationConvert {
	
	public static Location toBukkitLocation(entities.Location location) {
		World world = Bukkit.getWorld(location.getWorld());
		Location bukkitLocation = new Location(world, location.getX(), location.getY(), location.getZ());
		bukkitLocation.setPitch((float) location.getPitch());
		bukkitLocation.setYaw((float) location.getYaw());
		return bukkitLocation;
	}
	
	public static entities.Location toEntityLocation(Location bukkitLocation) {
		entities.Location location = new entities.Location();
		location.setX(bukkitLocation.getX());
		location.setY(bukkitLocation.getY());
		location.setZ(bukkitLocation.getZ());
		location.setPitch(bukkitLocation.getPitch());
		location.setYaw(bukkitLocation.getYaw());
		location.setWorld(bukkitLocation.getWorld().getName());
		return location;
	}
	
}
