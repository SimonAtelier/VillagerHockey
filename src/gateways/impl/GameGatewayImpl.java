package gateways.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import game.BaseGame;
import game.Game;
import game.VillagerSpawner;
import gateways.GameGateway;
import gateways.configuration.Configuration;
import usecases.displayteamscored.ScoreEventListener;
import usecases.jointeam.JoinTeamController;
import usecases.updatejoinsigns.UpdateJoinSignController;

public class GameGatewayImpl implements GameGateway {

	private final Object GAMES_MAP_LOCK = new Object();

	private HashMap<String, Game> games;

	public GameGatewayImpl() {
		games = new HashMap<String, Game>();
	}

	public void loadGames(Configuration configuration) {
		GamePersistanceYaml repository = new GamePersistanceYaml();
		File file = new File("plugins/VillagerHockey/games/");
		File[] files = file.listFiles();
		for (File f : files) {
			String name = f.getName().replace(".yml", "");
			Game game = repository.loadGame(name);
			setupFromPluginConfiguration(game, 	configuration);
			addGame(game);
			game.start();
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
		return findGameOfPlayer(uniquePlayerId) != null;
	}

	@Override
	public Game findGameOfPlayer(UUID uniquePlayerId) {
		synchronized (GAMES_MAP_LOCK) {
			for (Game game : games.values()) {
				if (game.getUniquePlayerIds().contains(uniquePlayerId))
					return game;
			}
		}
		return null;
	}

	@Override
	public void addGame(String name) {
		addGame(new BaseGame(name));
	}

	private boolean addGame(Game game) {
		if (game == null)
			return false;

		synchronized (GAMES_MAP_LOCK) {
			if (games.containsKey(game.getName()))
				return false;
			games.put(game.getName(), game);
			game.addTeamSelectListener(new JoinTeamController());
			game.addTeamScoreListener(new ScoreEventListener());
			new UpdateJoinSignController(game);
			return true;
		}
	}
	
	private void removeGame(Game game) {
		synchronized (GAMES_MAP_LOCK) {
			games.remove(game.getName());
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
		removeGame(findGameByName(game));
		new GamePersistanceYaml().deleteGame(findGameByName(game));
	}

}
