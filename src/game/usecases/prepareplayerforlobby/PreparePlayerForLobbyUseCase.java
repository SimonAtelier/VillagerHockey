package game.usecases.prepareplayerforlobby;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import config.Configuration;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerDataGateway;

public class PreparePlayerForLobbyUseCase implements PreparePlayerForLobby {
	
	private UUID uniquePlayerId;
	private Configuration configuration;
	private PermissionGateway permissionGateway;
	private PlayerDataGateway playerDataGateway;
	
	@Override
	public void execute(UUID uniquePlayerId, PreparePlayerForLobbyResponse response) {
		this.uniquePlayerId = uniquePlayerId;
		savePlayerData();
		
		Player player = Bukkit.getPlayer(uniquePlayerId);
		removeAllPotionEffects(player);
	
		PreparePlayerForLobbyResponseModel responseModel = new PreparePlayerForLobbyResponseModel();
		responseModel.setCanForceStart(permissionGateway.hasPermission(uniquePlayerId, Permissions.FORCE_START));
		responseModel.setCanSelectTeam(!configuration.isAutobalanceEnabled());
		responseModel.setCanViewAchievements(configuration.isAchievementsEnabled());
		responseModel.setGameMode(configuration.getLobbyGameMode());
		responseModel.setFoodLevel(20);
		responseModel.setLevel(0);
		responseModel.setMaxHealth(true);
		responseModel.setExperience(0);
		
		response.present(responseModel);
	}
	
	private void savePlayerData() {
		playerDataGateway.save(uniquePlayerId);
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

	@Override
	public void setPlayerDataGateway(PlayerDataGateway playerDataGateway) {
		this.playerDataGateway = playerDataGateway;
	}
		
}
