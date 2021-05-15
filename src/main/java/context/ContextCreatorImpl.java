package context;

import achievements.AchievementProvider;
import achievements.AchievementSystem;
import achievements.AchievementSystemController;
import achievements.AchievementSystemImpl;
import entities.config.Configuration;
import entities.config.ConfigurationLoader;
import gamestats.GameStatisticGateway;
import gamestats.InMemoryGameStatisticsGateway;
import gateways.impl.CommandGatewayImpl;
import gateways.impl.GameGatewayImpl;
import gateways.impl.InventoryGatewayYaml;
import gateways.impl.JoinSignsGatewayImpl;
import gateways.impl.PermissionGatewayImpl;
import gateways.impl.PlayerDataGatewayYaml;
import gateways.impl.PlayerGatewayImpl;
import gateways.impl.SignGatewayImpl;
import gateways.impl.TeamSpawnGatewayImpl;

public class ContextCreatorImpl implements ContextCreator {

	@Override
	public void createContext() {
		Context.configuration = loadConfiguration();
		Context.gameGateway = new GameGatewayImpl();
		Context.permissionGateway = new PermissionGatewayImpl();
		Context.inventoryGateway = new InventoryGatewayYaml();
		Context.playerGateway = new PlayerGatewayImpl();
		Context.commandGateway = new CommandGatewayImpl();
		Context.teamSpawnsGateway = new TeamSpawnGatewayImpl();
		Context.signGateway = new SignGatewayImpl();
		Context.joinSignGateway = new JoinSignsGatewayImpl();
		Context.playerDataGateway = new PlayerDataGatewayYaml();
		Context.achievementSystem = createAchievementSystem();
		Context.gameStatisticGateway = createGameStatisticsGateway(Context.achievementSystem);
	}
	
	private GameStatisticGateway createGameStatisticsGateway(AchievementSystem achievementSystem) {
		InMemoryGameStatisticsGateway gameStatisticsGateway = new InMemoryGameStatisticsGateway();
		gameStatisticsGateway.addPropertyChangeListener(new AchievementSystemController(achievementSystem));
		return gameStatisticsGateway;
	}
	
	private AchievementSystem createAchievementSystem() {
		AchievementSystem achievementSystem = new AchievementSystemImpl();
		achievementSystem.applyProvider(new AchievementProvider("plugins/VillagerHockey/achievements.json", "/achievements.json"));
		return achievementSystem;
	}
	
	private Configuration loadConfiguration() {
		ConfigurationLoader configurationLoader = new ConfigurationLoader();
		configurationLoader.setResourcePath("/config.yml");
		configurationLoader.setPluginFolderPath("plugins/VillagerHockey/config.yml");
		return configurationLoader.loadConfiguration();
	}

}
