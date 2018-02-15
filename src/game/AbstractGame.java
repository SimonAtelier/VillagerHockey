package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.JoinSigns;
import entities.Location;
import game.Event.GameStateChangeListener;
import game.States.GameState;

public abstract class AbstractGame implements Game {

	private int minimumPlayersToStart;
	private int playingTimeInSeconds;
	private String name;
	protected GameState gameState;
	private Location lobby;
	private JoinSigns joinSigns;
	protected List<UUID> players;
	private List<GameStateChangeListener> gameStateChangeListeners;

	public AbstractGame(String name) {
		this.name = name;
		joinSigns = new JoinSigns();
		players = new ArrayList<UUID>();
		gameStateChangeListeners = new ArrayList<GameStateChangeListener>();
	}
	
	private void fireGameStateChanged(GameState from, GameState to) {
		for (GameStateChangeListener listener : gameStateChangeListeners) {
			listener.onGameStateChanged(this, from, to);
		}
	}

	@Override
	public void addGameStateChangeListener(GameStateChangeListener listener) {
		if (listener == null)
			return;
		gameStateChangeListeners.add(listener);
	}

	@Override
	public void removeGameStateChangeListener(GameStateChangeListener listener) {
		gameStateChangeListeners.remove(listener);
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
	public JoinSigns getJoinSigns() {
		return joinSigns;
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
		GameState oldGameState = this.gameState;
		this.gameState = gameState;
		fireGameStateChanged(oldGameState, gameState);
	}
	
	@Override
	public boolean isMaximumAmountOfPlayersReached() {
		return getMaximumAmountOfPlayers() == getPlayersCount();
	}
	
	@Override
	public boolean canPlayerJoin(UUID player) {
		return getGameState().canPlayerJoin(this, player);
	}

}
