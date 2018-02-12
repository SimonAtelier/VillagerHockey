package usecases.JoinTeam;

import java.util.List;
import java.util.UUID;

import entities.Team;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class JoinTeamUseCase implements JoinTeam {

	private JoinTeamRequest request;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(JoinTeamRequest request, JoinTeamResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		if (noSuchGame()) {
			response.onNoSuchGame(request.getGame());
			return;
		}
		
		if (noSuchTeam()) {
			response.onNoSuchTeam(request.getTeam());
			return;
		}
		
		if (playerAlreadyJoinedATeam()) {
			response.onAlreadyJoinedATeam();
			return;
		}
		
		if (teamIsAlreadyFull()) {
			response.onTeamIsAlreadyFull(request.getTeam());
			return;
		}
		
		joinTeam();
		
		response.presentNowInTeam(request.getPlayer(), request.getTeam(), getTeamColor());
		response.presentTeamJoined(getPlayers(), getNameOfPlayer(), request.getTeam());
	}
	
	private boolean playerAlreadyJoinedATeam() {
		Game game = gameGateway.findGameByName(request.getGame());
		return game.getTeams().findTeamOfPlayer(request.getPlayer()) != null;
	}
	
	private boolean teamIsAlreadyFull() {
		Game game = gameGateway.findGameByName(request.getGame());
		Team team = game.getTeams().findTeamByName(request.getTeam());
		return team.getMaximumSize() == team.size();
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.JOIN_TEAM);
	}
	
	private int getTeamColor() {
		Game game = gameGateway.findGameByName(request.getGame());
		Team team = game.getTeams().findTeamByName(request.getTeam());
		return team.getColor().getRGB();
	}
	
	private void joinTeam() {
		Game game = gameGateway.findGameByName(request.getGame());
		Team team = game.getTeams().findTeamByName(request.getTeam());
		team.addPlayer(request.getPlayer());
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean noSuchTeam() {
		Game game = gameGateway.findGameByName(request.getGame());
		return !game.getTeams().containsTeamWithName(request.getTeam());
	}
	
	private String getNameOfPlayer() {
		return playerGateway.findPlayerNameById(request.getPlayer());
	}
	
	private List<UUID> getPlayers() {
		Game game = gameGateway.findGameByName(request.getGame());
		return game.getUniquePlayerIds();
	}
	
	private void setRequest(JoinTeamRequest request) {
		this.request = request;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

	@Override
	public void setPermissionGateway(PermissionGateway permissionGateway) {
		this.permissionGateway = permissionGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}
	
}
