package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Location;
import entities.Team;
import entities.Teams;
import game.States.RespawnGameState;
import game.States.StoppedGameState;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;
import usecases.LoadInventory.LoadInventoryController;
import view.impl.ScoreView;

public class BaseGame extends AbstractGame {

	private VillagerSpawner villagerSpawner;
	private Teams teams;
	private List<Goal> goals;

	public BaseGame(String name) {
		super(name);
		villagerSpawner = new VillagerSpawner();
		goals = new ArrayList<Goal>();
		teams = new Teams();
		gameState = new StoppedGameState();
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
		removePlayerFromTeam(player);
		restoreInventory(player);
		restorePlayerData(player);
		new ScoreView().hide(player);
		gameState.onPlayerLeave(this, player);
		gameChangeSupport.firePlayerLeave(player);
	}

	private void removePlayerFromTeam(UUID player) {
		Team team = teams.findTeamOfPlayer(player);
		if (team != null)
			team.removePlayer(player);
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

	private void restoreInventory(UUID player) {
		new LoadInventoryController().onLoadInventory(player);
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
