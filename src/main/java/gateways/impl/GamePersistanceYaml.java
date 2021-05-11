package gateways.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import context.Context;
import entities.Team;
import entities.TeamColor;
import game.BaseGame;
import game.Game;
import game.Goal;
import util.LocationConvert;

public class GamePersistanceYaml {
	
	public void deleteGame(Game game) {
		File file = new File("plugins/VillagerHockey/games/" + game.getName() + ".yml");
		if (file.exists()) {
			file.delete();
		}
	}

	public void saveGame(Game game) throws GatewayException {
		File file = new File("plugins/VillagerHockey/games/" + game.getName() + ".yml");

		try {

			if (!file.exists()) {
				file.createNewFile();
			}
			
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			yml.set("name", game.getName());
			yml.set("time", game.getPlayingTimeInSeconds());
			yml.set("minplayers", game.getMinimumPlayersToStart());
			yml.set("villagerspawn", game.getVillagerSpawner().getVillagerSpawnLocation());
			yml.set("lobby", LocationConvert.toBukkitLocation(game.getLobby()));

			List<Team> teams = game.getTeams().findAllTeams();
			
			List<String> teamNames = new ArrayList<String>();
			for (Team team : teams) {
				teamNames.add(team.getName());
			}
			
			yml.set("teams", teamNames);
			
			for (Team team : teams) {
				Goal goal = game.findGoalOfTeam(team.getName());
				Location loc1 = LocationConvert.toBukkitLocation(goal.getLocationOne());
				Location loc2 = LocationConvert.toBukkitLocation(goal.getLocationTwo());
				
				yml.set("team." + team.getName() + ".color", team.getColor().toString());
				yml.set("team." + team.getName() + ".goal.loc1", loc1);
				yml.set("team." + team.getName() + ".goal.loc2", loc2);
				
				List<Location> bukkitTeamSpawnLocations = new ArrayList<Location>();
				for (entities.Location loc : team.getSpawnLocations()) {
					bukkitTeamSpawnLocations.add(LocationConvert.toBukkitLocation(loc));
				}
				
				yml.set("team." + team.getName() + ".spawns", bukkitTeamSpawnLocations);
			}
			
			List<Location> bukkitLocations = new ArrayList<Location>();
			for (entities.Location loc : Context.joinSignGateway.findJoinSignLocations(game.getName())) {
				bukkitLocations.add(LocationConvert.toBukkitLocation(loc));
			}
			
			yml.set("signs", bukkitLocations);
			
			yml.save(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Game loadGame(String name) throws GatewayException {
		Game game = new BaseGame("");
		File file = new File("plugins/VillagerHockey/games/" + name + ".yml");

		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
			game.setName(yml.getString("name").trim());
			game.setPlayingTimeInSeconds(yml.getInt("time"));
			game.setMinimumPlayersToStart(yml.getInt("minplayers"));
			game.setVillagerSpawnLocation(LocationConvert.toEntityLocation((Location) yml.get("villagerspawn")));
			game.setLobby(LocationConvert.toEntityLocation((Location) yml.get("lobby")));

			for (Object o : yml.getList("teams")) {
				String name2 = (String) o;
				String name1 = "team." + (String) o;
				
				TeamColor color = TeamColor.valueOf((String) yml.get(name1 + ".color"));
				Team team = new Team(name2, color);
				game.getTeams().add(team);
				
				Goal goal = new Goal(game, name2);	
				game.addGoal(goal);
				
				Location loc1 = (Location)yml.get(name1 + ".goal.loc1");
				Location loc2 = (Location)yml.get(name1 + ".goal.loc2");
				
				goal.setLocationOne(LocationConvert.toEntityLocation(loc1));
				goal.setLocationTwo(LocationConvert.toEntityLocation(loc2));
				
				for (Object o1 : yml.getList(name1 + ".spawns")) {
					team.addSpawnLocation(LocationConvert.toEntityLocation((Location) o1));
				}
			}

			for (Object o : yml.getList("signs")) {
				Context.joinSignGateway.addLocation(game.getName(), LocationConvert.toEntityLocation((Location) o));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return game;
	}

}
