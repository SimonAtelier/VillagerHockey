package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import config.Configuration;
import context.Context;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;

public class PreparePlayerForLobbyUseCase implements PreparePlayerForLobby {
	
	private Configuration configuration;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(UUID uniquePlayerId, PreparePlayerForLobbyResponse response) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		savePlayerData(player);
		setPlayerLobbyData(player);
	
		PreparePlayerForLobbyResponseModel responseModel = new PreparePlayerForLobbyResponseModel();
		responseModel.setCanForceStart(permissionGateway.hasPermission(uniquePlayerId, Permissions.FORCE_START));
		responseModel.setCanSelectTeam(!configuration.isAutobalanceEnabled());
		
		response.present(responseModel);
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

	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}
		
}
