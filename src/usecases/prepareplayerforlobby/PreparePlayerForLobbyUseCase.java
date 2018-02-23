package usecases.prepareplayerforlobby;

import java.util.UUID;

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
		response.present(createResponseModel());
	}
	
	private PreparePlayerForLobbyResponseModel createResponseModel() {
		PreparePlayerForLobbyResponseModel responseModel = new PreparePlayerForLobbyResponseModel();
		responseModel.setCanForceStart(permissionGateway.hasPermission(uniquePlayerId, Permissions.FORCE_START));
		responseModel.setCanSelectTeam(!configuration.isAutobalanceEnabled());
		responseModel.setCanViewAchievements(configuration.isAchievementsEnabled());
		responseModel.setGameMode(configuration.getLobbyGameMode());
		responseModel.setFoodLevel(20);
		responseModel.setLevel(0);
		responseModel.setMaxHealth(true);
		responseModel.setExperience(0);
		responseModel.setRemoveAllPotionEffects(true);
		return responseModel;
	}
	
	private void savePlayerData() {
		playerDataGateway.save(uniquePlayerId);
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
