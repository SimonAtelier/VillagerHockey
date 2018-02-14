package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import context.Context;
import entities.JoinSigns;
import entities.Team;
import entities.Teams;
import game.CountDown.CountDown;
import game.CountDown.SecondsBasedCountDown;
import game.CountDown.Respawn.RespawnCountDownController;
import game.States.AnnounceWinnerGameState;
import game.States.GameState;
import game.States.StoppedGameState;
import game.States.WaitingGameState;
import gateways.InventoryGateway;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;
import main.MainPlugin;
import util.LocationConvert;
import view.impl.ScoreView;

public class Game implements IGame {

	private final Object PLAYERS_LOCK = new Object();

	private boolean canPlayersMove;
	private int minimumPlayersToStart;
	private int playingTimeInSeconds;
	
	private String name;
	private GameState gameState;
	private entities.Location lobby;
	private VillagerSpawner villagerSpawner;
	private Teams teams;
	private JoinSigns joinSigns;
	private CountDown respawnCountDown;
	private List<Goal> goals;
	private List<Player> players;
	
	private List<TeamSelectListener> listeners = new ArrayList<TeamSelectListener>();
	private List<GameListener> gameListeners = new ArrayList<GameListener>();

	public Game(String name) {
		canPlayersMove = true;
		playingTimeInSeconds = 300;
		this.name = name;
		villagerSpawner = new VillagerSpawner();
		players = new ArrayList<Player>();
		goals = new ArrayList<Goal>();
		teams = new Teams();
		joinSigns = new JoinSigns();
		initializeCountDowns();
		gameState = new StoppedGameState();
		setGameState(new WaitingGameState());
	}

	private void initializeCountDowns() {
		respawnCountDown = new SecondsBasedCountDown(MainPlugin.getInstance(), this, 3);
		respawnCountDown.setCountDownListener(new RespawnCountDownController());
	}

	public boolean canJoin(UUID player) {
		return gameState.canPlayerJoin(this, player);
	}

	public void join(UUID uniquePlayerId) {
		if (!canJoin(uniquePlayerId))
			return;

		Player player = Bukkit.getPlayer(uniquePlayerId);

		if (addPlayer(player)) {
			gameState.onPlayerJoin(this, uniquePlayerId);
			firePlayerJoin(uniquePlayerId);
		}
	}

	private boolean addPlayer(Player player) {
		synchronized (PLAYERS_LOCK) {
			if (player == null)
				return false;
			if (players.contains(player))
				return false;
			players.add(player);
			MainPlugin.getInstance().getGameManager().addPlayer(player.getUniqueId(), this);
			return true;
		}
	}
	
	public void selectLowestTeam(UUID player) {
		Team team = teams.findLowestTeam();
		fireTeamSelected(player, team.getName());
	}

	public void leave(UUID uniquePlayerId) {
		removePlayer(uniquePlayerId);
		teleportPlayerToLobby(uniquePlayerId);
		gameState.onPlayerLeave(this, uniquePlayerId);
		firePlayerLeave(uniquePlayerId);
	}

	private boolean removePlayer(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		synchronized (PLAYERS_LOCK) {
			if (player == null)
				return false;
			players.remove(player);
			MainPlugin.getInstance().getGameManager().removePlayer(player.getUniqueId());
			Team team = teams.findTeamOfPlayer(player.getUniqueId());
			if (team != null)
				team.removePlayer(player.getUniqueId());

			restoreInventory(player.getUniqueId());
			restorePlayerData(player);
			
			new ScoreView().hide(player.getUniqueId());
			
			return true;
		}
	}

	private void teleportPlayerToLobby(UUID uniquePlayerId) {
		Player player = Bukkit.getPlayer(uniquePlayerId);
		player.teleport(LocationConvert.toBukkitLocation(lobby));
	}

	private void teleportPlayersToLobby() {
		for (UUID uniquePlayerId : getUniquePlayerIds()) {
			teleportPlayerToLobby(uniquePlayerId);
		}
	}

	public void onTeamScored(String teamName) {
		Team team = teams.findTeamByName(teamName);
		team.setScore(team.getScore() + 1);
		fireTeamScored(teamName);
	}

	public List<UUID> getUniquePlayerIds() {
		List<UUID> players = new ArrayList<UUID>();
		synchronized (PLAYERS_LOCK) {
			for (Player player : this.players) {
				players.add(player.getUniqueId());
			}
		}
		return players;
	}

	public void removePlayers() {
		for (UUID player : getUniquePlayerIds()) {
			removePlayer(player);
		}
	}
	
	public void onGameCountDownFinished() {
		villagerSpawner.removeVillager();
		teleportPlayersToLobby();
		setGameState(new AnnounceWinnerGameState());
	}

	public void resetTeamScores() {
		teams.resetTeamScores();
	}

	private void teleportPlayersToTeamSpawns() {
		for (Team team : teams.findAllTeams()) {
			teleportPlayersToTeamSpawns(team);
		}
	}

	private void teleportPlayersToTeamSpawns(Team team) {
		List<UUID> players = team.getPlayers();
		List<entities.Location> locations = team.getSpawnLocations();
		for (int i = 0; i < players.size(); i++) {
			Player player = Bukkit.getPlayer(players.get(i));
			entities.Location location = locations.get(i);
			Location bukkitLocation = LocationConvert.toBukkitLocation(location);
			player.teleport(bukkitLocation);
		}
	}

	private void restoreInventory(UUID uniquePlayerId) {
		InventoryGateway inventoryGateway = Context.inventoryGateway;
		inventoryGateway.clearInventoryOfPlayer(uniquePlayerId);
		inventoryGateway.load(uniquePlayerId);
	}

	private void restorePlayerData(Player player) {
		PlayerDataGateway repository = new PlayerDataGatewayYaml();
		repository.load(player.getUniqueId());
	}

	public void warmUp() {
		respawnCountDown.start();
	}
	
	public void addGoal(Goal goal) {
		if (goal == null)
			return;
		goals.add(goal);
	}

	public void enterRespawnPhase() {
		setCanMove(false);
		teleportPlayersToTeamSpawns();
		gameState.onEnterRespawnPhase(this);
	}

	public void leaveRespawnPhase() {
		getVillagerSpawner().spawnVillager();
		setCanMove(true);
		gameState.onLeaveRespawnPhase(this);
	}

	public void addGameListener(GameListener listener) {
		gameListeners.add(listener);
	}

	public void removeGameListener(GameListener listener) {
		gameListeners.remove(listener);
	}

	public void addTeamSelectListener(TeamSelectListener listener) {
		listeners.add(listener);
	}

	public void removeTeamSelectListener(TeamSelectListener listener) {
		listeners.remove(listener);
	}

	private void fireTeamSelected(UUID player, String team) {
		for (TeamSelectListener listener : listeners) {
			listener.onTeamSelected(player, name, team);
		}
	}

	private void fireTeamScored(String team) {
		for (GameListener listener : getGameListeners()) {
			listener.onTeamScored(getName(), team);
		}
	}

	private void firePlayerLeave(UUID player) {
		for (GameListener listener : getGameListeners()) {
			listener.onPlayerLeave(this, player);
		}
	}

	private void firePlayerJoin(UUID player) {
		for (GameListener listener : getGameListeners()) {
			listener.onPlayerJoin(this, player);
		}
	}

	private void fireGameStateChanged(GameState from, GameState to) {
		for (GameListener listener : getGameListeners()) {
			listener.onGameStateChanged(this, from, to);
		}
	}

	private List<GameListener> getGameListeners() {
		return new ArrayList<GameListener>(gameListeners);
	}

	public Goal findGoalOfTeam(String team) {
		for (int i = 0; i < goals.size(); i++) {
			Goal goal = goals.get(i);
			if (goal.getTeam().equals(team)) {
				return goal;
			}
		}
		return null;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		GameState oldGameState = this.gameState;
		oldGameState.leaveGameState(this);
		gameState.enterGameState(this);
		this.gameState = gameState;
		fireGameStateChanged(oldGameState, gameState);
	}

	public Teams getTeams() {
		return teams;
	}

	public boolean isCanMove() {
		return canPlayersMove;
	}

	public void setCanMove(boolean canMove) {
		this.canPlayersMove = canMove;
	}

	public boolean isMaximumAmountOfPlayersReached() {
		return getTeams().getMaximumAmountOfPlayers() == getPlayersCount();
	}

	public boolean isLobbySet() {
		return getLobby() != null;
	}

	public VillagerSpawner getVillagerSpawner() {
		return villagerSpawner;
	}

	public boolean isVillageSpawnLocationSet() {
		return villagerSpawner.getVillagerSpawnLocation() != null;
	}

	public void setVillagerSpawnLocation(entities.Location location) {
		villagerSpawner.setVillagerSpawnLocation(LocationConvert.toBukkitLocation(location));
	}

	@Override
	public int getPlayersCount() {
		return players.size();
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
	public entities.Location getLobby() {
		return lobby;
	}

	@Override
	public void setLobby(entities.Location lobby) {
		this.lobby = lobby;
	}

	@Override
	public JoinSigns getJoinSigns() {
		return joinSigns;
	}

}
