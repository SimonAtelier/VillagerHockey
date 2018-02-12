package main;

import java.io.File;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import context.Context;
import gateways.CommandGateway;
import gateways.Configuration;
import gateways.impl.ConfigurationYaml;
import gateways.impl.GameManager;
import spigot.ArgumentsWithLabel;
import usecases.AddGame.AddGameCommand;
import usecases.AddTeam.AddTeamCommand;
import usecases.AddTeamSpawn.AddTeamSpawnCommand;
import usecases.ExecuteCommand.ExecuteCommand;
import usecases.ExecuteCommand.ExecuteCommand.ExecuteCommandResponse;
import usecases.ExecuteCommand.ExecuteCommandController;
import usecases.ExecuteCommand.ExecuteCommandPresenter;
import usecases.ExecuteCommand.ExecuteCommandUseCase;
import usecases.ExecuteCommand.ExecuteCommandView;
import usecases.ExecuteCommand.ExecuteCommandViewImpl;
import usecases.JoinGame.JoinGameCommand;
import usecases.KickPlayer.KickPlayerCommand;
import usecases.LeaveGame.LeaveGameCommand;
import usecases.ListGames.ListGamesCommand;
import usecases.PerformAction.BreakBlock.BreakBlockEventListener;
import usecases.PerformAction.BreakJoinSign.BreakJoinSignEventListener;
import usecases.PerformAction.ChangeFoodLevel.ChangeFoodLevelEventListener;
import usecases.PerformAction.ClickInventory.ClickInventoryEventListener;
import usecases.PerformAction.ClickJoinSign.ClickJoinSignEventListener;
import usecases.PerformAction.ConsumeItem.ConsumeItemEventListener;
import usecases.PerformAction.DamageItem.DamageItemEventListener;
import usecases.PerformAction.DropItem.DropItemEventListener;
import usecases.PerformAction.Move.MoveEventListener;
import usecases.PerformAction.OpenInventory.OpenInventoryEventListener;
import usecases.PerformAction.PickupItem.PickupItemEventListener;
import usecases.PerformAction.PlaceJoinSign.PlaceJoinSignEventListener;
import usecases.PerformAction.Quit.QuitEventListener;
import usecases.PerformAction.ReceiveDamage.ReceiveDamageEventListener;
import usecases.PerformAction.ShootPuck.ShootPuckEventListener;
import usecases.SaveGame.SaveGameCommand;
import usecases.SelectTeam.ShowTeamsEventListener;
import usecases.SetGoal.SetGoalCommand;
import usecases.SetLobby.SetLobbyCommand;
import usecases.SetMinPlayers.SetMinPlayersCommand;
import usecases.SetPlayingTime.SetPlayingTimeCommand;
import usecases.SetVillagerSpawn.SetVillagerSpawnCommand;
import usecases.ShowHelp.ShowHelpCommand;

public class MainPlugin extends JavaPlugin implements CommandExecutor {

	private static final String PREFIX = "[VillagerHockey] ";
	public static String chatPrefix = "";

	private static MainPlugin instance;
	private GameManager gameManager;
	private Configuration configuration;
	
	@Override
	public void onLoad() {
		sendConsoleMessage(PREFIX + "loading...");
		sendConsoleMessage(PREFIX + "loaded.");
	}

	@Override
	public void onEnable() {
		sendConsoleMessage(PREFIX + "enabling...");
		initializePlugin();
		createOrUpdatePluginFolders();
		loadConfiguration();
		createContext();
		loadGames();
		registerCommands();
		createAndRegisterEventListeners();
		sendConsoleMessage(PREFIX + "enabled.");
	}

	@Override
	public void onDisable() {
		sendConsoleMessage(PREFIX + "disabling...");
		unloadGames();
		sendConsoleMessage(PREFIX + "disabled.");
	}
	
	private void createContext() {
		Context.gameGateway = getGameManager();
	}
	
	private void sendConsoleMessage(String message) {
		System.out.println(message);
	}
	
	private void loadGames() {
		gameManager.loadGames();
	}
	
	private void unloadGames() {
		gameManager.unloadGames();
	}

	private void loadConfiguration() {
		File file = new File("plugins/VillagerHockey/config.yml");
		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
		configuration = new ConfigurationYaml(yamlConfiguration);
		chatPrefix = configuration.getPrefix();
	}

	private void initializePlugin() {
		instance = this;
		gameManager = new GameManager();
	}

	private void registerCommands() {
		CommandGateway commandGateway = Context.commandGateway;
		
		commandGateway.registerCommand(new AddGameCommand());
		commandGateway.registerCommand(new AddTeamSpawnCommand());
		commandGateway.registerCommand(new JoinGameCommand());
		commandGateway.registerCommand(new KickPlayerCommand());
		commandGateway.registerCommand(new LeaveGameCommand());
		commandGateway.registerCommand(new ListGamesCommand());
		commandGateway.registerCommand(new SaveGameCommand());
		commandGateway.registerCommand(new SetGoalCommand());
		commandGateway.registerCommand(new SetLobbyCommand());
		commandGateway.registerCommand(new SetVillagerSpawnCommand());
		commandGateway.registerCommand(new ShowHelpCommand());
		commandGateway.registerCommand(new SetMinPlayersCommand());
		commandGateway.registerCommand(new SetPlayingTimeCommand());
		commandGateway.registerCommand(new AddTeamCommand());
		
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

	public GameManager getGameManager() {
		return gameManager;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public static MainPlugin getInstance() {
		return instance;
	}

}
