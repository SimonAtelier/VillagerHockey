package usecases.LeaveTeam;

import java.util.List;
import java.util.UUID;

import entities.Team;
import game.Game;
import gateways.GameGateway;
import gateways.PlayerGateway;

public class LeaveTeamUseCase implements LeaveTeam {

	private Team team;
	private Game game;
	private LeaveTeamRequest request;
	private GameGateway gameGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(LeaveTeamRequest request, LeaveTeamResponse response) {
		setRequest(request);
		
		if (playerNotIngame()) {
			return;
		}
		
		findGameOfPlayer();
		findTeamOfPlayer();
		
		if (playerNotInATeam()) {
			return;
		}
		
		List<UUID> players = game.getUniquePlayerIds();
		
		leaveTeam();
		
		response.presentLeftTeam(request.getPlayer(), team.getName());
		response.presentLeftTeam(players, getNameOfPlayer(), team.getName());
	}
	
	private String getNameOfPlayer() {
		return playerGateway.findPlayerNameById(request.getPlayer());
	}
	
	private void findGameOfPlayer() {
		game = gameGateway.findGameOfPlayer(request.getPlayer());
	}
	
	private void findTeamOfPlayer() {
		Game game = gameGateway.findGameOfPlayer(request.getPlayer());
		team = game.getTeams().findTeamOfPlayer(request.getPlayer());
	}
	
	private void leaveTeam() {
		Team team = game.getTeams().findTeamOfPlayer(request.getPlayer());
		team.removePlayer(request.getPlayer());
	}
	
	private boolean playerNotInATeam() {
		return team == null;
	}
	
	private boolean playerNotIngame() {
		return !gameGateway.isIngame(request.getPlayer());
	}
	
	private void setRequest(LeaveTeamRequest request) {
		this.request = request;
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
