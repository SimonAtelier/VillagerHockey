package test.entities;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import entities.Location;
import entities.Team;
import entities.TeamColor;

public class TeamTest {

	@Test
	public void constructTeamWithColorAndNameGetReturnsValues() {
		String name = "Team-1";
		TeamColor color = TeamColor.YELLOW;
		Team team = new Team(name, color);
		Assert.assertEquals(color, team.getColor());
		Assert.assertEquals(name, team.getName());
	}
	
	@Test
	public void setColorGetColorReturnsValue() {
		String name = "Team-2";
		TeamColor color = TeamColor.AQUA;
		Team team = new Team(name, color);
		team.setColor(TeamColor.YELLOW);
		Assert.assertEquals(TeamColor.YELLOW, team.getColor());
	}
	
	@Test
	public void setNameGetNameReturnsValue() {
		String name = "Team-3";
		String newName = "Team-4";
		TeamColor color = TeamColor.BLACK;
		Team team = new Team(name, color);
		team.setName(newName);
		Assert.assertEquals(newName, team.getName());
	}
	
	@Test
	public void getPlayersReturnsEmptyListByDefault() {
		String name = "Team-5";
		TeamColor color = TeamColor.DARK_AQUA;
		Team team = new Team(name, color);
		List<UUID> players = team.getPlayers();
		Assert.assertTrue(players.isEmpty());
	}
	
	@Test
	public void addAndRemovePlayerGetPlayersReturnsEmptyList() {
		UUID player = UUID.randomUUID();
		Team team = new Team("Team", TeamColor.BLACK);
		team.addPlayer(player);
		team.removePlayer(player);
		Assert.assertTrue(team.getPlayers().isEmpty());
	}
	
	@Test
	public void addPlayerGetPlayersReturnsListWithSizeOne() {
		String name = "Team-5";
		TeamColor color = TeamColor.DARK_AQUA;
		Team team = new Team(name, color);
		UUID player = UUID.randomUUID();
		team.addPlayer(player);
		Assert.assertEquals(1, team.getPlayers().size());
	}
	
	@Test
	public void addPlayerGetSizeReturnsOne() {
		Team team = new Team("Team", TeamColor.BLUE);
		team.addPlayer(UUID.randomUUID());
		Assert.assertEquals(1, team.size());
	}
	
	@Test
	public void addRandomAmountOfPlayersGetSizeReturnsRandomAmount() {
		Team team = new Team("Team", TeamColor.BLUE);
		int random = (int) (Math.random() * 1000);
		for (int i = 0; i < random; i++) {
			team.addPlayer(UUID.randomUUID());
		}
		Assert.assertEquals(random, team.size());
	}
	
	@Test
	public void removePlayerFromReturnedListGetPlayersStillContainsPlayer() {
		String name = "Team-5";
		TeamColor color = TeamColor.DARK_AQUA;
		Team team = new Team(name, color);
		UUID player = UUID.randomUUID();
		team.addPlayer(player);
		team.getPlayers().remove(player);
		Assert.assertEquals(1, team.getPlayers().size());
		Assert.assertTrue(team.getPlayers().contains(player));
	}
	
	@Test
	public void addSamePlayerTwiceGetPlayersReturnsListWithSizeOne() {
		String name = "Team-6";
		TeamColor color = TeamColor.DARK_GRAY;
		Team team = new Team(name, color);
		UUID player = UUID.randomUUID();
		team.addPlayer(player);
		team.addPlayer(player);
		Assert.assertEquals(1, team.getPlayers().size());
	}
	
	@Test
	public void addNullPlayerGetPlayersReturnsEmptyList() {
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		team.addPlayer(null);
		Assert.assertTrue(team.getPlayers().isEmpty());
	}
	
	@Test
	public void scoreReturnsZeroByDefault() {
		Team team = new Team("Team", TeamColor.BLACK);
		Assert.assertEquals(0, team.getScore());
	}
	
	@Test
	public void setScoreGetScoreReturnsValue() {
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		team.setScore(20);
		Assert.assertEquals(20, team.getScore());
	}
	
	@Test
	public void setRandomScoreGetScoreReturnsRandomScore() {
		int random = (int) (Math.random() * Integer.MAX_VALUE);
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		team.setScore(random);
		Assert.assertEquals(random, team.getScore());
	}
	
	@Test
	public void getSpawnLocationsReturnsEmptyListByDefault() {
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		Assert.assertTrue(team.getSpawnLocations().isEmpty());
	}
	
	@Test
	public void addSpawnLocationGetSpawnLocationsReturnsListWithSizeOne() {
		Location location = new Location();
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		team.addSpawnLocation(location);
		Assert.assertEquals(1, team.getSpawnLocations().size());
	}
	
	@Test
	public void addNullSpawnLocationGetSpawnLocationsReturnsEmptyList() {
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		team.addSpawnLocation(null);
		Assert.assertTrue(team.getSpawnLocations().isEmpty());
	}
	
	@Test
	public void addSpawnLocationGetMaximumSizeReturnsOne() {
		Team team = new Team("Team", TeamColor.DARK_GRAY);
		team.addSpawnLocation(new Location());
		Assert.assertEquals(1, team.getMaximumSize());
	}
	
}
