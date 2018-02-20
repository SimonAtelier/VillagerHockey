package main;

import java.io.File;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import context.Context;
import entities.command.ArgumentsWithLabel;
import gateways.CommandGateway;
import gateways.Configuration;
import gateways.impl.CommandGatewayImpl;
import gateways.impl.GameGatewayImpl;
import gateways.impl.InventoryGatewayYaml;
import gateways.impl.JoinSignsGatewayImpl;
import gateways.impl.PermissionGatewayAlwaysTrueMock;
import gateways.impl.PlayerGatewayImpl;
import gateways.impl.SignGatewayImpl;
import gateways.impl.TeamSpawnGatewayImpl;
import usecases.executecommand.ExecuteCommand;
import usecases.executecommand.ExecuteCommandController;
import usecases.executecommand.ExecuteCommandPresenter;
import usecases.executecommand.ExecuteCommandUseCase;
import usecases.executecommand.ExecuteCommandView;
import usecases.executecommand.ExecuteCommandViewImpl;
import usecases.executecommand.ExecuteCommand.ExecuteCommandResponse;
import usecases.performaction.breakblock.BreakBlockEventListener;
import usecases.performaction.breakjoinsign.BreakJoinSignEventListener;
import usecases.performaction.changefoodlevel.ChangeFoodLevelEventListener;
import usecases.performaction.clickinventory.ClickInventoryEventListener;
import usecases.performaction.clickjoinsign.ClickJoinSignEventListener;
import usecases.performaction.consumeitem.ConsumeItemEventListener;
import usecases.performaction.damageitem.DamageItemEventListener;
import usecases.performaction.dropitem.DropItemEventListener;
import usecases.performaction.move.MoveEventListener;
import usecases.performaction.openinventory.OpenInventoryEventListener;
import usecases.performaction.pickupitem.PickupItemEventListener;
import usecases.performaction.placejoinsign.PlaceJoinSignEventListener;
import usecases.performaction.quit.QuitEventListener;
import usecases.performaction.receivedamage.ReceiveDamageEventListener;
import usecases.performaction.shootpuck.ShootPuckEventListener;
import usecases.selectteam.ShowTeamsEventListener;
import view.impl.MessageViewImpl;

public class MainPlugin extends JavaPlugin implements CommandExecutor {

	private static MainPlugin instance;
	private GameGatewayImpl gameGateway;
	private Configuration configuration;

	@Override
	public void onEnable() {
		createOrUpdatePluginFolders();
		loadConfiguration();
		initializePlugin();
		createContext();
		loadGames();
		registerCommands();
		createAndRegisterEventListeners();
	}

	@Override
	public void onDisable() {
		unloadGames();
	}

	private void createContext() {
		this.gameGateway = new GameGatewayImpl();
		Context.permissionGateway = new PermissionGatewayAlwaysTrueMock();
		Context.inventoryGateway = new InventoryGatewayYaml();
		Context.gameGateway = this.gameGateway;
		Context.playerGateway = new PlayerGatewayImpl();
		Context.messageView = new MessageViewImpl();
		Context.commandGateway = new CommandGatewayImpl();
		Context.teamSpawnsGateway = new TeamSpawnGatewayImpl();
		Context.signGateway = new SignGatewayImpl();
		Context.joinSignGateway = new JoinSignsGatewayImpl();
		
		Context.messageView.setPrefix(configuration.getPrefix());
	}

	private void loadGames() {
		gameGateway.loadGames(configuration);
	}

	private void unloadGames() {
		gameGateway.unloadGames();
	}

	private void loadConfiguration() {
		ConfigurationLoader configurationLoader = new ConfigurationLoader();
		configurationLoader.setResourcePath("/resources/config.yml");
		configurationLoader.setPluginFolderPath("plugins/VillagerHockey/config.yml");
		configuration = configurationLoader.loadConfiguration();
	}

	private void initializePlugin() {
		instance = this;
	}

	private void registerCommands() {
		CommandGateway commandGateway = Context.commandGateway;
		new CommandProviderImpl().registerCommands(commandGateway);
		getCommand("vh").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		Player player = (Player) sender;

		ArgumentsWithLabel argumentsWithLabel = new ArgumentsWithLabel();
		handlePlayerCommand(player.getUniqueId(), argumentsWithLabel.create(label, args));

		return true;
	}

	public void handlePlayerCommand(UUID player, String[] arguments) {
		ExecuteCommand useCase = new ExecuteCommandUseCase();
		ExecuteCommandView view = new ExecuteCommandViewImpl(player);
		ExecuteCommandResponse presenter = new ExecuteCommandPresenter(view);
		ExecuteCommandController controller = new ExecuteCommandController(useCase, presenter);
		useCase.setCommandGateway(Context.commandGateway);
		controller.handleRequest(player, arguments);
	}

	private void createAndRegisterEventListeners() {
		registerEventListener(new BreakBlockEventListener());
		registerEventListener(new ChangeFoodLevelEventListener());
		registerEventListener(new ConsumeItemEventListener());
		registerEventListener(new DamageItemEventListener());
		registerEventListener(new DropItemEventListener());
		registerEventListener(new MoveEventListener());
		registerEventListener(new PickupItemEventListener());
		registerEventListener(new QuitEventListener());
		registerEventListener(new OpenInventoryEventListener());
		registerEventListener(new ClickInventoryEventListener());
		registerEventListener(new ClickJoinSignEventListener());
		registerEventListener(new PlaceJoinSignEventListener());
		registerEventListener(new BreakJoinSignEventListener());
		registerEventListener(new ShowTeamsEventListener());
		registerEventListener(new ShootPuckEventListener());
		registerEventListener(new ReceiveDamageEventListener());
	}

	private void registerEventListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	private void createOrUpdatePluginFolders() {
		String[] paths = { "plugins/VillagerHockey/inventories/", "plugins/VillagerHockey/games/",
				"plugins/VillagerHockey/playerdata/" };
		for (String path : paths) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	public GameGatewayImpl getGameGateway() {
		return gameGateway;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public static MainPlugin getInstance() {
		return instance;
	}

}
