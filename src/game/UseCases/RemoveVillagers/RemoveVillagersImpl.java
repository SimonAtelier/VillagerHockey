package game.UseCases.RemoveVillagers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;

import game.Game;
import gateways.GameGateway;

public class RemoveVillagersImpl implements RemoveVillagers {
	
	private GameGateway gameGateway;
	
	@Override
	public void execute(String game) {
		Game gameObject = gameGateway.findGameByName(game);
		Location location = gameObject.getVillagerSpawner().getVillagerSpawnLocation();
		World world = Bukkit.getServer().getWorld(location.getWorld().getName());
		List<LivingEntity> entities = world.getLivingEntities();
		for (LivingEntity entity : entities) {
			if (entity instanceof Villager) {
				entity.remove();
			}
		}
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}