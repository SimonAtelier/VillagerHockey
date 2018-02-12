package gateways.impl;

import context.Context;
import entities.Location;
import entities.Team;
import game.Game;
import gateways.TeamSpawnsGateway;

public class TeamSpawnGatewayImpl implements TeamSpawnsGateway {

	@Override
	public void addTeamSpawn(String game, String team, Location location) {
		Game game1 = Context.gameGateway.findGameByName(game);
		Team team1 = game1.getTeams().findTeamByName(team);
		team1.addSpawnLocation(location);
	}
	
}
