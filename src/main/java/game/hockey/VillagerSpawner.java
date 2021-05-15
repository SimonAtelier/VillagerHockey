package game.hockey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.simonatelier.save.Exporter;
import io.github.simonatelier.save.Importer;
import io.github.simonatelier.save.Input;
import io.github.simonatelier.save.Output;
import io.github.simonatelier.save.Savable;
import util.LocationConvert;

public class VillagerSpawner implements Savable {

	private boolean passenger;
	private boolean aiEnabled;
	private boolean randomVillagerNamesEnabled;
	private String villagerName;
	private Location villagerSpawnLocation;
	private Villager villager;
	private List<String> randomNames;
	private Entity passengerEntity;
	
	public VillagerSpawner() {
		villagerName = " ";
		randomNames = new ArrayList<String>();
	}

	public void refreshVillagerName() {
		String villagerName = randomVillagerNamesEnabled ? getRandomName() : this.villagerName;
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
		removePassenger();
		if (villager == null)
			return;
		villager.remove();
		villager = null;
	}
	
	private void removePassenger() {
		if (passengerEntity == null)
			return;
		passengerEntity.remove();
		passengerEntity = null;
	}

	public void setCustomVillagerName(String name) {
		if (villager == null)
			return;
		villager.setCustomName(name);
		villager.setCustomNameVisible(true);
	}

	public void spawnParticleAtVillagerLocation(Particle particle, int amount, int offsetY) {
		if (villager == null)
			return;
		if (particle == null)
			return;
		if (amount <= 0)
			return;
		World world = villager.getWorld();
		Location location = villager.getLocation();
		world.spawnParticle(particle, location.getX(), location.getY() + offsetY, location.getZ(), amount);
	}

	private void spawnVillagerAtLocation(Location location) {
		if (location == null)
			return;
		World world = location.getWorld();
		Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
		refreshVillagerName();
		
		// Work around
		villager.setAI(true);
		if (!aiEnabled)
			villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));		
		villager.setVillagerLevel(5);
		villager.setRemoveWhenFarAway(false);	
		villager.setAdult();
		villager.setProfession(Profession.LIBRARIAN);
		villager.setCustomName(villagerName);
		villager.setCustomNameVisible(true);
		villager.setCanPickupItems(false);
		this.villager = villager;
	}
	
	public void spawnPassenger() {
		if (villager == null)
			return;
		Location location = villager.getLocation();
		Entity entity = location.getWorld().spawnEntity(location, EntityType.PIG);
		passengerEntity = entity;
		villager.setPassenger(passengerEntity);
	}

	public boolean isAIEnabled() {
		return aiEnabled;
	}

	public void setAIEnabled(boolean aiEnabled) {
		this.aiEnabled = aiEnabled;
		if (villager == null)
			return;
		if (aiEnabled)
			villager.removePotionEffect(PotionEffectType.SLOW);
		else
			villager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
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
	
	public void setCustomName(String customName) {
		if (villager == null)
			return;
		villager.setCustomName(customName);
	}
	
	public void setCustomPassengerName(String customName) {
		if (passengerEntity == null)
			return;
		passengerEntity.setCustomName(customName);
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

	public boolean isPassenger() {
		return passenger;
	}

	public void setPassenger(boolean passenger) {
		this.passenger = passenger;
	}
	
	public boolean isPassengerAlive() {
		if (passengerEntity == null)
			return false;
		return !passengerEntity.isDead();
	}
	
	@Override
	public void write(Exporter exporter) throws IOException {
		Location location = villagerSpawnLocation;
		Output output = exporter.getOutput(this);
		output.write(location.getX(), "villagerSpawnerX");
		output.write(location.getY(), "villagerSpawnerY");
		output.write(location.getZ(), "villagerSpawnerZ");
		output.write(location.getWorld().getName(), "villagerSpawnerWorld");
	}

	@Override
	public void read(Importer importer) throws IOException {
		Input input = importer.getInput(this);
		double x = input.readDouble("villagerSpawnerX");
		double y = input.readDouble("villagerSpawnerY");
		double z = input.readDouble("villagerSpawnerZ");
		String worldName = input.readString("villagerSpawnerWorld");
		entities.Location location = new entities.Location();
		location.setX(x);
		location.setY(y);
		location.setZ(z);
		location.setWorld(worldName);
		setVillagerSpawnLocation(location);
	}

}
