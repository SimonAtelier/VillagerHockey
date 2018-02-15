package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import entities.Team;
import entities.Teams;
import game.States.RespawnGameState;
import game.States.StoppedGameState;
import game.States.WaitingGameState;
import gateways.InventoryGateway;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;
import main.MainPlugin;
import view.impl.ScoreView;

public class BaseGame extends AbstractGame {

	private final Object PLAYERS_LOCK = new Object();

	private VillagerSpawner villagerSpawner;
	private Teams teams;
	private List<Goal> goals;
	
	public BaseGame(String name) {
		super(name);
		villagerSpawner = new VillagerSpawner();
		goals = new ArrayList<Goal>();
		teams = new Teams();
		gameState = new StoppedGameState();
		gameState.transitionToGameState(this, new WaitingGameState());
	}

	@Override
	public void join(UUID player) {
		if (!canPlayerJoin(player))
			return;
		if (addPlayer(player)) {
			gameState.onPlayerJoin(this, player);
			gameChangeSupport.firePlayerJoin(player);
		}
	}
	
	@Override
	public void leave(UUID player) {
		removePlayer(player);
		gameState.onPlayerLeave(this, player);
		gameChangeSupport.firePlayerLeave(player);
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
		gameChangeSupport.fireTeamSelected(player, team.getName());
	}

	public void onTeamScored(String teamName) {
		Team team = teams.findTeamByName(teamName);
		team.setScore(team.getScore() + 1);
		gameChangeSupport.fireTeamScored(teamName);
		warmUp();
	}

	public void removePlayers() {
		for (UUID player : getUniquePlayerIds()) {
			removePlayer(player);
		}
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
	
	public VillagerSpawner getVillagerSpawner() {
		return villagerSpawner;
	}

	public void setVillagerSpawnLocation(Location location) {
		villagerSpawner.setVillagerSpawnLocation(location);
	}

}
