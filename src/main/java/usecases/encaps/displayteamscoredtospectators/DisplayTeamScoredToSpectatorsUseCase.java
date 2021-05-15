package usecases.encaps.displayteamscoredtospectators;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Location;
import entities.Team;
import game.Game;
import game.hockey.HockeyGameCycle;
import gateways.GameGateway;
import gateways.PlayerGateway;
import usecases.hockey.displayteamscored.ScoreResponseItem;
import util.LocationConvert;

public class DisplayTeamScoredToSpectatorsUseCase implements DisplayTeamScoredToSpectators {

	private boolean spectatorMessagesEnabled = true;
	private int radius = 100;
	private Game game;
	private Location villagerSpawnLocation;
	private GameGateway gameGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(String game, String team, DisplayTeamScoredToSpectatorsResponse response) {
		if (!spectatorMessagesEnabled)
			return;
		
		this.game = findGameByName(game);
		villagerSpawnLocation = findVillagerSpawnLocation();
		List<UUID> viewers = findSpectators();
		response.presentTeamScored(viewers, team);
		response.presentScore(viewers, createResponseItems());
	}
	
	private List<ScoreResponseItem> createResponseItems() {
		List<ScoreResponseItem> responseItems = new ArrayList<ScoreResponseItem>();
		for (Team team : findAllTeamsOfGame()) {
			ScoreResponseItem responseItem = new ScoreResponseItem();
			responseItem.setTeam(team.getName());
			responseItem.setScore(team.getScore());
			responseItems.add(responseItem);
		}
		return responseItems;
	}
	
	private List<Team> findAllTeamsOfGame() {
		return game.getTeams().findAllTeams();
	}
	
	private List<UUID> findSpectators() {
		List<UUID> spectators = new ArrayList<UUID>();
		
		for (UUID uniquePlayerId : findAllOnlinePlayers()) {
			if (!isIngame(uniquePlayerId) && isInRange(uniquePlayerId)) {
				spectators.add(uniquePlayerId);
			}
		}
		
		return spectators;
	}
	
	private boolean isInRange(UUID uniquePlayerId) {
		Location location = findLocationOfPlayer(uniquePlayerId);
		if (!location.getWorld().equals(villagerSpawnLocation.getWorld()))
			return false;
		return villagerSpawnLocation.distance(location.getX(), location.getY(), location.getZ()) < radius;
	}
	
	private Location findVillagerSpawnLocation() {
		HockeyGameCycle hockey = (HockeyGameCycle) game.getGameCycle();
		return LocationConvert.toEntityLocation(hockey.getVillagerSpawner().getVillagerSpawnLocation());
	}
	
	private Game findGameByName(String game) {
		return gameGateway.findGameByName(game);
	}
	
	private Location findLocationOfPlayer(UUID uniquePlayerId) {
		return playerGateway.findLocationOfPlayer(uniquePlayerId);
	}

	private boolean isIngame(UUID uniquePlayerId) {
		return gameGateway.isIngame(uniquePlayerId);
	}
	
	private List<UUID> findAllOnlinePlayers() {
		return playerGateway.findAllOnlinePlayers();
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

}
