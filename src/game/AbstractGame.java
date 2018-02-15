package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.JoinSigns;
import entities.Location;
import game.Event.GameChangeSupport;
import game.Event.GameStateChangeListener;
import game.Event.JoinListener;
import game.Event.LeaveListener;
import game.Event.TeamScoreListener;
import game.Event.TeamSelectListener;
import game.States.GameState;

public abstract class AbstractGame implements Game {

	private int minimumPlayersToStart;
	private int playingTimeInSeconds;
	private String name;
	protected GameState gameState;
	private Location lobby;
	private JoinSigns joinSigns;
	protected List<UUID> players;
	protected GameChangeSupport gameChangeSupport;

	public AbstractGame(String name) {
		this.name = name;
		joinSigns = new JoinSigns();
		players = new ArrayList<UUID>();
		gameChangeSupport = new GameChangeSupport(this);
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

}
