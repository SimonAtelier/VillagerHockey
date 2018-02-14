package gateways.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import game.Game;
import game.VillagerSpawner;
import gateways.Configuration;
import gateways.GameGateway;
import main.MainPlugin;
import usecases.DisplayTeamScored.ScoreEventListener;
import usecases.JoinTeam.JoinTeamController;
import usecases.UpdateJoinSigns.UpdateJoinSignController;

public class GameManager implements GameGateway {

	private final Object GAMES_MAP_LOCK = new Object();
	private final Object PLAYERS_MAP_LOCK = new Object();

	private HashMap<String, Game> games;
	private HashMap<UUID, String> players;

	public GameManager() {
		games = new HashMap<String, Game>();
		players = new HashMap<UUID, String>();
	}

	public void loadGames() {
		GamePersistanceYaml repository = new GamePersistanceYaml();
		File file = new File("plugins/VillagerHockey/games/");
		File[] files = file.listFiles();
		for (File f : files) {
			String name = f.getName().replace(".yml", "");
			Game game = repository.loadGame(name);
			setupFromPluginConfiguration(game, MainPlugin.getInstance().getConfiguration());
			addGame(game);
		}
	}

	public void unloadGames() {
		for (Game game : findAllGames()) {
			game.getVillagerSpawner().removeVillager();
			for (UUID uniquePlayerId : game.getUniquePlayerIds()) {
				game.leave(uniquePlayerId);
			}
		}
	}

	public void setupFromPluginConfiguration(Game game, Configuration configuration) {
		VillagerSpawner villagerSpawner = game.getVillagerSpawner();
		villagerSpawner.setAIEnabled(configuration.isVillagerAIEnabled());
		villagerSpawner.setVillagerName(configuration.getVillagerName());
		villagerSpawner.setRandomVillagerNamesEnabled(configuration.isUseRandomVillagerNamesEnabled());
		villagerSpawner.setRandomNames(configuration.getRandomVillagerNames());
	}

	@Override
	public boolean isIngame(UUID uniquePlayerId) {
		synchronized (PLAYERS_MAP_LOCK) {
			return players.containsKey(uniquePlayerId);
		}
	}

	public void addPlayer(UUID uniquePlayerId, Game game) {
		synchronized (PLAYERS_MAP_LOCK) {
			players.put(uniquePlayerId, game.getName());
		}
	}

	public void removePlayer(UUID uniquePlayerId) {
		synchronized (PLAYERS_MAP_LOCK) {
			players.remove(uniquePlayerId);
		}
	}

	@Override
	public Game getGameOfPlayer(UUID uniquePlayerId) {
		synchronized (PLAYERS_MAP_LOCK) {
			String name = players.get(uniquePlayerId);
			return findGameByName(name);
		}
	}

	@Override
	public boolean addGame(String name) {
		Game game = new Game(name);
		return addGame(game);
	}

	private boolean addGame(Game game) {
		if (game == null)
			return false;

		synchronized (GAMES_MAP_LOCK) {
			if (games.containsKey(game.getName()))
				return false;
			UpdateJoinSignController controller = new UpdateJoinSignController();
			game.addTeamSelectListener(new JoinTeamController());
			game.addGameListener(controller);
			game.addGameStateChangeListener(controller);
			game.addGameListener(new ScoreEventListener());
			games.put(game.getName(), game);
			return true;
		}
	}

	@Override
	public Game findGameByName(String name) {
		synchronized (GAMES_MAP_LOCK) {
			return games.get(name);
		}
	}

	@Override
	public boolean containsGame(String name) {
		synchronized (GAMES_MAP_LOCK) {
			return games.containsKey(name);
		}
	}

	private List<Game> findAllGames() {
		List<Game> games = new ArrayList<Game>();
		synchronized (games) {
			for (Game game : this.games.values()) {
				games.add(game);
			}
		}
		return games;
	}

	@Override
	public List<String> findAllGameNames() {
		List<String> games = new ArrayList<String>();
		synchronized (games) {
			for (Game game : this.games.values()) {
				games.add(game.getName());
			}
		}
		return games;
	}

	@Override
	public Game loadGame(String name) throws GatewayException {
		return new GamePersistanceYaml().loadGame(name);
	}

	@Override
	public void saveGame(String game) throws GatewayException {
		new GamePersistanceYaml().saveGame(findGameByName(game));
	}

	@Override
	public void deleteGame(String game) {
		new GamePersistanceYaml().deleteGame(findGameByName(game));
	}

}
