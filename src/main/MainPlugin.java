package main;

import java.io.File;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import context.Context;
import context.ContextCreatorImpl;
import entities.command.ArgumentsWithLabel;
import gateways.CommandGateway;
import usecases.executecommand.ExecuteCommand;
import usecases.executecommand.ExecuteCommand.ExecuteCommandResponse;
import usecases.executecommand.ExecuteCommandController;
import usecases.executecommand.ExecuteCommandPresenter;
import usecases.executecommand.ExecuteCommandUseCase;
import usecases.executecommand.ExecuteCommandView;
import usecases.executecommand.ExecuteCommandViewImpl;
import usecases.executecommand.RootCommandLabel;

public class MainPlugin extends JavaPlugin implements CommandExecutor {

	private static MainPlugin instance;

	@Override
	public void onEnable() {
		initializePlugin();
		createOrUpdatePluginFolders();
		createContext();
		loadGames();
		registerCommands();
		registerListeners();
	}

	@Override
	public void onDisable() {
		unloadGames();
	}

	private void createContext() {
		new ContextCreatorImpl().createContext();
	}

	private void loadGames() {
		Context.gameGateway.loadGames();
	}

	private void unloadGames() {
		Context.gameGateway.unloadGames();
	}

	private void initializePlugin() {
		instance = this;
	}

	private void registerCommands() {
		CommandGateway commandGateway = Context.commandGateway;
		new CommandProviderImpl().registerCommands(commandGateway);
		getCommand(RootCommandLabel.ROOT_COMMAND_LABEL).setExecutor(this);
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

	private void registerListeners() {
		new ListenerRegistration().registerListeners(this);
	}

	private void createOrUpdatePluginFolders() {
		String[] paths = { "plugins/VillagerHockey/games/", "plugins/VillagerHockey/playerdata/" };
		for (String path : paths) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	public static MainPlugin getInstance() {
		return instance;
	}

}
