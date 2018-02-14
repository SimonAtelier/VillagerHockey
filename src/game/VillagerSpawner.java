package game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import util.LocationConvert;

public class VillagerSpawner {

	private boolean aiEnabled;
	private boolean randomVillagerNamesEnabled;
	private String villagerName;
	private Location villagerSpawnLocation;
	private Villager villager;
	private List<String> randomNames;
	
	public VillagerSpawner() {
		villagerName = " ";
		randomNames = new ArrayList<String>();
	}

	public void refreshVillagerName() {
		String villagerName = randomVillagerNamesEnabled ? getRandomName()
				: this.villagerName;
		this.villagerName = villagerName;
	}

	public void spawnVillager() {
		if (villagerSpawnLocation == null)
			return;
		if (villager != null)
			return;
		spawnVillagerAtLocation(villagerSpawnLocation);
	}

	public void removeVillager() {
		if (villager == null)
			return;
		villager.remove();
		villager = null;
	}

	public void setCustomVillagerName(String name) {
		if (villager == null)
			return;
		villager.setCustomName(name);
		villager.setCustomNameVisible(true);
	}

	public void spawnParticleAtVillagerLocation(Particle particle, int amount,
			int offsetY) {
		if (villager == null)
			return;
		if (particle == null)
			if (amount <= 0)
				return;
		World world = villager.getWorld();
		Location location = villager.getLocation();
		world.spawnParticle(particle, location.getX(), location.getY()
				+ offsetY, location.getZ(), amount);
	}

	public void spawnVillagerAtLocation(Location location) {
		if (location == null)
			return;
		World world = location.getWorld();
		Villager villager = (Villager) world.spawnEntity(location,
				EntityType.VILLAGER);
		refreshVillagerName();
		villager.setAI(aiEnabled);
		villager.setAdult();
		villager.setProfession(Profession.LIBRARIAN);
		villager.setCustomName(villagerName);
		villager.setCustomNameVisible(true);
		villager.setCanPickupItems(false);
		this.villager = villager;
	}
	
	public boolean isAIEnabled() {
		return aiEnabled;
	}

	public void setAIEnabled(boolean aiEnabled) {
		this.aiEnabled = aiEnabled;
	}

	public String getRandomName() {
		int random = (int) (Math.random() * randomNames.size());
		return randomNames.get(random);
	}

	public String getCustomVillagerName() {
		if (villager == null)
			return "";
		String customName = villager.getCustomName();
		if (customName != null)
			return customName;
		return "";
	}

	public Location getVillagerLocation() {
		if (villager != null)
			return villager.getLocation();
		return null;
	}
	
	public Location getVillagerSpawnLocation() {
		return villagerSpawnLocation;
	}

	public void setVillagerSpawnLocation(entities.Location villagerSpawnLocation) {
		if (villagerSpawnLocation == null)
			return;
		this.villagerSpawnLocation = LocationConvert.toBukkitLocation(villagerSpawnLocation);
	}

	public Villager getVillager() {
		return villager;
	}

	public boolean isRandomVillagerNamesEnabled() {
		return randomVillagerNamesEnabled;
	}

	public void setRandomVillagerNamesEnabled(boolean randomVillagerNamesEnabled) {
		this.randomVillagerNamesEnabled = randomVillagerNamesEnabled;
	}

	public String getVillagerName() {
		return villagerName;
	}

	public void setVillagerName(String villagerName) {
		this.villagerName = villagerName;
	}

	public void setRandomNames(List<String> randomNames) {
		this.randomNames.clear();
		this.randomNames.addAll(randomNames);
	}

}
