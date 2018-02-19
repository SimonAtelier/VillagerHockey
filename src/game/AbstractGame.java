package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Location;
import entities.Teams;
import game.Event.GameChangeSupport;
import game.Event.GameStateChangeListener;
import game.Event.JoinListener;
import game.Event.LeaveListener;
import game.Event.TeamScoreListener;
import game.Event.TeamSelectListener;
import game.States.GameState;
import game.States.StoppedGameState;
import game.States.WaitingGameState;

public abstract class AbstractGame implements Game {

	private final Object PLAYERS_LOCK = new Object();

	private boolean started;
	private int minimumPlayersToStart;
	private int playingTimeInSeconds;
	private String name;
	private GameState gameState;
	private GameLoop gameLoop;
	private Location lobby;
	private Teams teams;
	protected List<UUID> players;
	protected GameChangeSupport gameChangeSupport;

	public AbstractGame(String name) {
		this.name = name;
		gameState = new StoppedGameState();
		gameLoop = new GameLoop(this);
		teams = new Teams();
		players = new ArrayList<UUID>();
		gameChangeSupport = new GameChangeSupport(this);
	}

	protected boolean addPlayer(UUID player) {
		synchronized (PLAYERS_LOCK) {
			if (player == null)
				return false;
			if (players.contains(player))
				return false;
			players.add(player);
			return true;
		}
	}

	protected boolean removePlayer(UUID player) {
		synchronized (PLAYERS_LOCK) {
			if (player == null)
				return false;
			if (!players.contains(player))
				return false;
			players.remove(player);
			return true;
		}
	}

	@Override
	public void tick() {
		gameState.onTick(this);
	}

	@Override
	public void start() {
		if (started) {
			return;
		}
		started = true;
		getGameState().transitionToGameState(this, new WaitingGameState());
	}

	@Override
	public void stop() {
		if (!started)
			return;
		gameLoop.stop();
		getGameState().transitionToGameState(this, new StoppedGameState());
		started = false;
	}

	@Override
	public void join(UUID player) {
		if (!canPlayerJoin(player))
			return;

		if (addPlayer(player)) {
			getGameState().onPlayerJoin(this, player);
			gameChangeSupport.firePlayerJoin(player);
		}
		
		if (getPlayersCount() == 1)
			gameLoop.start();
	}

	@Override
	public void leave(UUID player) {
		removePlayer(player);
		getGameState().onPlayerLeave(this, player);
		gameChangeSupport.firePlayerLeave(player);
		if (getPlayersCount() == 0) {
			gameLoop.stop();
		}
	}

	@Override
	public void leaveAll() {
		for (UUID player : getUniquePlayerIds()) {
			leave(player);
		}
	}

	@Override
	public void addGameStateChangeListener(GameStateChangeListener listener) {
		gameChangeSupport.addGameStateChangeListener(listener);
	}

	@Override
	public void removeGameStateChangeListener(GameStateChangeListener listener) {
		gameChangeSupport.removeGameStateChangeListener(listener);
	}

	@Override
	public void addJoinListener(JoinListener listener) {
		gameChangeSupport.addJoinListener(listener);
	}

	@Override
	public void removeJoinListener(JoinListener listener) {
		gameChangeSupport.removeJoinListener(listener);
	}

	@Override
	public void addLeaveListener(LeaveListener listener) {
		gameChangeSupport.addLeaveListener(listener);
	}

	@Override
	public void removeLeaveListener(LeaveListener listener) {
		gameChangeSupport.removeLeaveListener(listener);
	}

	@Override
	public void addTeamSelectListener(TeamSelectListener listener) {
		gameChangeSupport.addTeamSelectListener(listener);
	}

	@Override
	public void removeTeamSelectListener(TeamSelectListener listener) {
		gameChangeSupport.removeTeamSelectListener(listener);
	}

	@Override
	public void addTeamScoreListener(TeamScoreListener listener) {
		gameChangeSupport.addTeamScoreListener(listener);
	}

	@Override
	public void removeTeamScoreListener(TeamScoreListener listener) {
		gameChangeSupport.removeTeamScoreListener(listener);
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public int getMinimumPlayersToStart() {
		return minimumPlayersToStart;
	}

	@Override
	public void setMinimumPlayersToStart(int minimumPlayersToStart) {
		this.minimumPlayersToStart = minimumPlayersToStart;
	}

	@Override
	public int getMaximumAmountOfPlayers() {
		return getTeams().getMaximumAmountOfPlayers();
	}

	@Override
	public int getPlayingTimeInSeconds() {
		return playingTimeInSeconds;
	}

	@Override
	public void setPlayingTimeInSeconds(int playingTimeInSeconds) {
		this.playingTimeInSeconds = playingTimeInSeconds;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Location getLobby() {
		return lobby;
	}

	@Override
	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}

	@Override
	public int getPlayersCount() {
		return players.size();
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	@Override
	public void setGameState(GameState gameState) {
		if (!started) {
			return;
		}
		GameState oldGameState = this.gameState;
		this.gameState = gameState;
		gameChangeSupport.fireGameStateChanged(oldGameState, gameState);
	}

	@Override
	public boolean isMaximumAmountOfPlayersReached() {
		return getMaximumAmountOfPlayers() == getPlayersCount();
	}

	@Override
	public boolean canPlayerJoin(UUID player) {
		return getGameState().canPlayerJoin(this, player);
	}

	@Override
	public List<UUID> getUniquePlayerIds() {
		List<UUID> players = new ArrayList<UUID>();
		synchronized (PLAYERS_LOCK) {
			for (UUID player : this.players) {
				players.add(player);
			}
		}
		return players;
	}

	@Override
	public Teams getTeams() {
		return teams;
	}

}
