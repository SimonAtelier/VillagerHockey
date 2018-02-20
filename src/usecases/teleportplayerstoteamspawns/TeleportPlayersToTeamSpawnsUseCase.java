package usecases.teleportplayerstoteamspawns;

import entities.Team;
import entities.Teams;
import game.Game;
import gateways.GameGateway;

public class TeleportPlayersToTeamSpawnsUseCase implements TeleportPlayersToTeamSpawns {

	private String game;
	private Teams teams;
	private GameGateway gameGateway;
	
	@Override
	public void execute(String game, TeleportPlayersToTeamSpawnsResponse response) {
		setGame(game);
		findTeams();
		
		for (Team team : teams.findAllTeams()) {
			response.presentLocation(team.getPlayers(), team.getSpawnLocations());
		}
		
	}
		
	private void findTeams() {
		Game game = gameGateway.findGameByName(this.game);
		teams = game.getTeams();
	}
	
	private void setGame(String game) {
		this.game = game;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
