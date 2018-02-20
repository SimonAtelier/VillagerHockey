package usecases.savegame;

import java.util.List;
import java.util.UUID;

import entities.Team;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class SaveGameUseCase implements SaveGame {

	private String name;
	private UUID player;
	private Game game;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	
	@Override
	public void execute(UUID player, String name, SaveGameResponse response) {		
		this.player = player;
		this.name = name;
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
				
		if (noSuchGame()) {
			response.onNoSuchGame(name);
			return;
		}
		
		findGame();
		
		if (numberOfTeamsIsNotTwo()) {
			response.onCannotSaveNumberOfTeamsIsNotTwo();
			return;
		}
		
		if (noLobbySet()) {
			response.onCannotSaveGameNoLobbySet();
			return;
		}
		
		if (noVillagerSpawnSet()) {
			response.onCannotSaveGameNoVillagerSpawnSet();
			return;
		}
		
		if (playingTimeIsLessOrEqualToZero()) {
			response.onCannotSavePlayingTimeIsLessOrEqualToZero();
			return;
		}
		
		if (notAllGoalsSet()) {
			response.onCannotSaveNotAllGoalsSet();
			return;
		}
		
		if (teamSpawnLocationsMissing()) {
			response.onCannotSaveSpawnLocationsMissing();
			return;
		}
		
		if (amountOfTeamSpawnsIsNotEqual()) {
			response.onCannotSaveAmountOfTeamSpawnsIsNotEqual();
			return;
		}
		
		save();
		response.onGameSuccessfullySaved();
	}
	
	private boolean notAllGoalsSet() {
		for (Team team : game.getTeams().findAllTeams()) {
			if (game.findGoalOfTeam(team.getName()) == null)
				return true;
		}
		return false;
	}
	
	private boolean teamSpawnLocationsMissing() {
		for (Team team : game.getTeams().findAllTeams()) {
			if (team.getSpawnLocations().isEmpty())
				return true;
		}
		return false;
	}
	
	private boolean amountOfTeamSpawnsIsNotEqual() {
		List<Team> teams = game.getTeams().findAllTeams();
		int size = teams.get(0).getMaximumSize();
		boolean equalSize = true;
		for (Team team : teams) {
			equalSize &= (size == team.getMaximumSize());
		}
		return !equalSize;
	}
	
	private void save() {
		gameGateway.saveGame(name);
	}
	
	private void findGame() {
		game = gameGateway.findGameByName(name);
	}
	
	private boolean numberOfTeamsIsNotTwo() {
		return game.getTeams().getNumberOfTeams() != 2;
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(name);
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(player, Permissions.SAVE_GAME);
	}
	
	private boolean playingTimeIsLessOrEqualToZero() {
		return game.getPlayingTimeInSeconds() <= 0;
	}
	
	private boolean noLobbySet() {
		return game.getLobby() == null;
	}
	
	private boolean noVillagerSpawnSet() {
		return game.getVillagerSpawner().getVillagerSpawnLocation() == null;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}
	
}
