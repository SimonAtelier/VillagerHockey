package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import context.Context;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;

public class PreparePlayerForLobbyUseCase implements PreparePlayerForLobby {
	
	@Override
	public void execute(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		savePlayerData(player);
		setPlayerLobbyData(player);
		new LobbyMenu().display(uniquePlayerId);
	}
		
	private void setPlayerLobbyData(Player player) {
		player.setGameMode(GameMode.valueOf(Context.configuration.getLobbyGameMode()));
		player.setExp(0);
		player.setLevel(0);
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		removeAllPotionEffects(player);
	}
	
	private void savePlayerData(Player player) {
		PlayerDataGateway gateway = new PlayerDataGatewayYaml();
		gateway.save(player.getUniqueId());
	}
	
	private void removeAllPotionEffects(Player player) {
		for (PotionEffect effect : player.getActivePotionEffects()) {
			if (player.hasPotionEffect(effect.getType()))
				player.removePotionEffect(effect.getType());
		}
	}
		
}
