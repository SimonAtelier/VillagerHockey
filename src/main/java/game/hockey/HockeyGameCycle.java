package game.hockey;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Villager;

import context.Context;
import entities.Location;
import entities.Team;
import entities.config.Configuration;
import game.Game;
import game.GameCycle;
import game.hockey.Goal.GoalResponse;
import game.hockey.states.RespawnGameState;
import game.states.RunningGameState;
import io.github.simonatelier.save.Exporter;
import io.github.simonatelier.save.Importer;
import io.github.simonatelier.save.Savable;
import usecases.encaps.displaywinner.DisplayWinnerController;
import usecases.hockey.updatestatistics.UpdateStatisticsController;
import util.LocationConvert;
import view.score.HockeyScoreView;

public class HockeyGameCycle implements GameCycle, Savable {

	private boolean canLeaveVehicle;
	private boolean goalsEnabled;
	private VillagerSpawner villagerSpawner;
	public HockeyGame hockeyGame;
	private Game game;

	public HockeyGameCycle(Game game, HockeyGame hockeyGame) {
		this.game = game;
		this.hockeyGame = hockeyGame;
		this.goalsEnabled = true;
		villagerSpawner = new VillagerSpawner();
	}

	@Override
	public void onLoad() {
		setupSpawner();
	}

	@Override
	public void onUnload() {
		removeVillager();
	}

	@Override
	public void onPlayerLeave(UUID player) {
		removeVehicle(player);
		new HockeyScoreView().hide(player);
	}
	
	private void removeVehicle(UUID player) {
		Context.playerGateway.removeVehicle(player);
	}

	@Override
	public void onEnterWaitingGameState() {
		removeVillager();
	}

	@Override
	public void onEnterAnnounceWinner() {
		removeVillager();
		updateStatistics();
		displayWinner();
	}

	private void setupSpawner() {
		Configuration configuration = Context.configuration;
		villagerSpawner.setAIEnabled(configuration.isVillagerAIEnabled());
		villagerSpawner.setVillagerName(configuration.getVillagerName());
		villagerSpawner.setRandomVillagerNamesEnabled(configuration.isUseRandomVillagerNamesEnabled());
		villagerSpawner.setRandomNames(configuration.getRandomVillagerNames());
	}

	@Override
	public void onTickRunning(RunningGameState runningGameState) {
		GoalResponse goalResponse = checkGoal();
		if (goalResponse == null)
			return;
		removeVillager();
		onTeamScored(goalResponse.getTeam(), 1);
		runningGameState.transitionToGameState(new RespawnGameState(runningGameState));
	}
	
	public void onTeamScored(String teamName, int score) {
		Team team = game.getTeams().findTeamByName(teamName);
		team.setScore(team.getScore() + score);
		game.getChangeSupport().fireTeamScored(teamName);
	}
	
	public GoalResponse checkGoal() {
		if (!isGoalsEnabled())
			return null;

		Villager villager = villagerSpawner.getVillager();
		
		if (villager == null)
			return null;
		
		Location location = LocationConvert.toEntityLocation(villager.getLocation());

		for (Team team : game.getTeams().findAllTeams()) {
			Goal goal = hockeyGame.findGoalOfTeam(team.getName());
			GoalResponse response = goal.check(location);
			if (response.isSored())
				return response;
		}

		return null;
	}

	private void displayWinner() {
		new DisplayWinnerController().onDisplayWinner(game.getName());
	}

	public void removeVillager() {
		getVillagerSpawner().removeVillager();
	}

	private void updateStatistics() {
		new UpdateStatisticsController().onUpdate(game.getName());
	}

	public boolean isCanLeaveVehicle() {
		return canLeaveVehicle;
	}

	public void setCanLeaveVehicle(boolean canLeaveVehicle) {
		this.canLeaveVehicle = canLeaveVehicle;
	}

	public boolean isGoalsEnabled() {
		return goalsEnabled;
	}

	public void setGoalsEnabled(boolean goalsEnabled) {
		this.goalsEnabled = goalsEnabled;
	}

	public VillagerSpawner getVillagerSpawner() {
		return villagerSpawner;
	}

	public void setVillagerSpawnLocation(Location location) {
		villagerSpawner.setVillagerSpawnLocation(location);
	}

	@Override
	public void write(Exporter exporter) throws IOException {
		getVillagerSpawner().write(exporter);
	}

	@Override
	public void read(Importer importer) throws IOException {
		getVillagerSpawner().read(importer);
	}

}
