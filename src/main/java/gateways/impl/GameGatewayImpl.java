package gateways.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import achievements.AchievementSystemController;
import context.Context;
import game.Game;
import game.hockey.HockeyGameImpl;
import gamestats.GameStatisticsController;
import gateways.GameGateway;
import usecases.api.jointeam.JoinTeamController;
import usecases.api.updatejoinsigns.UpdateJoinSignController;
import usecases.hockey.displayteamscored.ScoreEventListener;

public class GameGatewayImpl implements GameGateway {

	private final Object GAMES_MAP_LOCK = new Object();

	private String path;
	private HashMap<String, Game> games;

	public GameGatewayImpl(String path) {
		this.path = path;
		games = new HashMap<String, Game>();
	}

	@Override
	public void loadGames() {
		GamePersistanceYaml repository = new GamePersistanceYaml(path);
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			String name = f.getName().replace(".yml", "");
			Game game = repository.loadGame(name);
			game.onLoad();
			addGame(game);
			game.start();
		}
	}

	@Override
	public void unloadGames() {
		for (Game game : findAllGames()) {
			game.onUnload();
		}
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
		addGame(new HockeyGameImpl(name));
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
			new GameStatisticsController(game);
			game.addJoinListener(new AchievementSystemController(Context.achievementSystem));
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
		return new GamePersistanceYaml(path).loadGame(name);
	}

	@Override
	public void saveGame(String game) throws GatewayException {
		new GamePersistanceYaml(path).saveGame(findGameByName(game));
	}

	@Override
	public void deleteGame(String game) {
		removeGame(findGameByName(game));
		new GamePersistanceYaml(path).deleteGame(findGameByName(game));
	}

}
