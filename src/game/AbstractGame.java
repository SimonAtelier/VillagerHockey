package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.JoinSigns;
import entities.Location;
import game.States.GameState;

public abstract class AbstractGame implements IGame {
	
	private int minimumPlayersToStart;
	private int playingTimeInSeconds;
	private String name;
	protected GameState gameState;
	private Location lobby;
	private JoinSigns joinSigns;
	protected List<UUID> players;
	
	public AbstractGame(String name) {
		this.name = name;
		this.joinSigns = new JoinSigns();
		this.players = new ArrayList<UUID>();
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
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
		
}
