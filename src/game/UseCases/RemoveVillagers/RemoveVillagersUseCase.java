package game.UseCases.RemoveVillagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;

import game.Game;
import gateways.GameGateway;

public class RemoveVillagersUseCase implements RemoveVillagers {
	
	private GameGateway gameGateway;
	
	@Override
	public void execute(String game) {
		Game gameObject = gameGateway.findGameByName(game);
		Location location = gameObject.getVillagerSpawner().getVillagerSpawnLocation();
		World world = Bukkit.getServer().getWorld(location.getWorld().getName());
		for (Entity entity : world.getEntities()) {
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