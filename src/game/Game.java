package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import entities.Team;
import entities.Teams;
import game.Event.GameListener;
import game.Event.TeamSelectListener;
import game.States.RespawnGameState;
import game.States.StoppedGameState;
import game.States.WaitingGameState;
import gateways.InventoryGateway;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;
import main.MainPlugin;
import view.impl.ScoreView;

public class Game extends AbstractGame {

	private final Object PLAYERS_LOCK = new Object();

	private boolean canPlayersMove;
	private VillagerSpawner villagerSpawner;
	private Teams teams;
	private List<Goal> goals;

	private List<TeamSelectListener> listeners = new ArrayList<TeamSelectListener>();
	private List<GameListener> gameListeners = new ArrayList<GameListener>();

	public Game(String name) {
		super(name);
		canPlayersMove = true;
		villagerSpawner = new VillagerSpawner();
		goals = new ArrayList<Goal>();
		teams = new Teams();
		gameState = new StoppedGameState();
		gameState.transitionToGameState(this, new WaitingGameState());
	}

	@Override
	public boolean canPlayerJoin(UUID player) {
		return gameState.canPlayerJoin(this, player);
	}

	public void join(UUID player) {
		if (!canPlayerJoin(player))
			return;

		if (addPlayer(player)) {
			gameState.onPlayerJoin(this, player);
			firePlayerJoin(player);
		}
	}

	private boolean addPlayer(UUID player) {
		synchronized (PLAYERS_LOCK) {
			if (player == null)
				return false;
			if (players.contains(player))
				return false;
			players.add(player);
			MainPlugin.getInstance().getGameManager().addPlayer(player, this);
			return true;
		}
	}

	public void leave(UUID uniquePlayerId) {
		removePlayer(uniquePlayerId);
		gameState.onPlayerLeave(this, uniquePlayerId);
		firePlayerLeave(uniquePlayerId);
	}

	private boolean removePlayer(UUID player) {
		synchronized (PLAYERS_LOCK) {
			if (player == null)
				return false;
			players.remove(player);
			MainPlugin.getInstance().getGameManager().removePlayer(player);
			Team team = teams.findTeamOfPlayer(player);
			if (team != null)
				team.removePlayer(player);

			restoreInventory(player);
			restorePlayerData(player);

			new ScoreView().hide(player);

			return true;
		}
	}

	public void selectLowestTeam(UUID player) {
		Team team = teams.findLowestTeam();
		fireTeamSelected(player, team.getName());
	}

	public void onTeamScored(String teamName) {
		Team team = teams.findTeamByName(teamName);
		team.setScore(team.getScore() + 1);
		fireTeamScored(teamName);
		warmUp();
	}

	public void removePlayers() {
		for (UUID player : getUniquePlayerIds()) {
			removePlayer(player);
		}
	}
	
	public List<UUID> getUniquePlayerIds() {
		List<UUID> players = new ArrayList<UUID>();
		synchronized (PLAYERS_LOCK) {
			for (UUID player : this.players) {
				players.add(player);
			}
		}
		return players;
	}

	private void restoreInventory(UUID uniquePlayerId) {
		InventoryGateway inventoryGateway = Context.inventoryGateway;
		inventoryGateway.clearInventoryOfPlayer(uniquePlayerId);
		inventoryGateway.load(uniquePlayerId);
	}

	private void restorePlayerData(UUID player) {
		PlayerDataGateway repository = new PlayerDataGatewayYaml();
		repository.load(player);
	}

	private void warmUp() {
		gameState.transitionToGameState(this, new RespawnGameState(gameState));
	}

	public void addGoal(Goal goal) {
		if (goal == null)
			return;
		goals.add(goal);
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

	public Teams getTeams() {
		return teams;
	}

	public boolean isCanMove() {
		return canPlayersMove;
	}

	public void setCanMove(boolean canMove) {
		this.canPlayersMove = canMove;
	}
	
	public VillagerSpawner getVillagerSpawner() {
		return villagerSpawner;
	}

	public void setVillagerSpawnLocation(Location location) {
		villagerSpawner.setVillagerSpawnLocation(location);
	}
	
	// -------------------------------------------------------------------------------
	
	@Override
	public int getMaximumAmountOfPlayers() {
		return getTeams().getMaximumAmountOfPlayers();
	}
	
	// -------------------------------------------------------------------------------
	// Listeners
	// -------------------------------------------------------------------------------
	
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
			listener.onTeamSelected(player, getName(), team);
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

	private List<GameListener> getGameListeners() {
		return new ArrayList<GameListener>(gameListeners);
	}

}
