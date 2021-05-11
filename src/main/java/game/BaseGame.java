package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import context.Context;
import entities.Location;
import entities.Team;
import game.Goal.GoalResponse;
import game.event.LeaveListener;
import gateways.PlayerDataGateway;
import gateways.impl.PlayerDataGatewayYaml;
import usecases.api.loadinventory.LoadInventoryController;
import view.score.ScoreView;

public class BaseGame extends AbstractGame implements LeaveListener {

	private boolean canLeaveVehicle;
	private boolean goalsEnabled;
	private VillagerSpawner villagerSpawner;
	private List<Goal> goals;

	public BaseGame(String name) {
		super(name);
		goalsEnabled = true;
		villagerSpawner = new VillagerSpawner();
		goals = new ArrayList<Goal>();
		addLeaveListener(this);
	}
	
	@Override
	public GoalResponse checkGoal() {
		for (Team team : getTeams().findAllTeams()) {
			Goal goal = findGoalOfTeam(team.getName());
			GoalResponse response = goal.check();
			if (response.isSored())
				return response;
		}
		return null;
	}
	
	@Override
	public void onPlayerLeave(Game game, UUID player) {
		removeVehicle(player);
		removePlayerFromTeam(player);
		restoreInventory(player);
		restorePlayerData(player);
		new ScoreView().hide(player);
	}
	
	private void removeVehicle(UUID player) {
		Context.playerGateway.removeVehicle(player);
	}

	private void removePlayerFromTeam(UUID player) {
		getTeams().removePlayer(player);
	}
	
	private void restoreInventory(UUID player) {
		new LoadInventoryController().onLoadInventory(player);
	}

	private void restorePlayerData(UUID player) {
		PlayerDataGateway playerDataGateway = new PlayerDataGatewayYaml();
		playerDataGateway.load(player);
	}

	@Override
	public void selectLowestTeam(UUID player) {
		Team team = getTeams().findLowestTeam();
		gameChangeSupport.fireTeamSelected(player, team.getName());
	}

	@Override
	public void onTeamScored(String teamName, int score) {
		Team team = getTeams().findTeamByName(teamName);
		team.setScore(team.getScore() + score);
		gameChangeSupport.fireTeamScored(teamName);
	}

	@Override
	public void addGoal(Goal goal) {
		if (goal == null)
			return;
		goals.add(goal);
	}

	@Override
	public Goal findGoalOfTeam(String team) {
		for (int i = 0; i < goals.size(); i++) {
			Goal goal = goals.get(i);
			if (goal.getTeam().equals(team)) {
				return goal;
			}
		}
		return null;
	}

	@Override
	public VillagerSpawner getVillagerSpawner() {
		return villagerSpawner;
	}

	@Override
	public void setVillagerSpawnLocation(Location location) {
		villagerSpawner.setVillagerSpawnLocation(location);
	}

	@Override
	public void setGoalsEnabled(boolean goalsEnabled) {
		this.goalsEnabled = goalsEnabled;
	}

	@Override
	public boolean isGoalsEnabled() {
		return goalsEnabled;
	}

	@Override
	public boolean isCanLeaveVehicle() {
		return canLeaveVehicle;
	}

	@Override
	public void setCanLeaveVehicle(boolean canLeaveVehicle) {
		this.canLeaveVehicle = canLeaveVehicle;
	}
	
}
