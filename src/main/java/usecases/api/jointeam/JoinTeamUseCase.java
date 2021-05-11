package usecases.api.jointeam;

import java.util.List;
import java.util.UUID;

import entities.Team;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class JoinTeamUseCase implements JoinTeam {

	private Game game;
	private Team team;
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
		
		initializeGame();
		
		if (noSuchTeam()) {
			response.onNoSuchTeam(request.getTeam());
			return;
		}
		
		initializeTeam();
		
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
	
	private void initializeGame() {
		game = gameGateway.findGameByName(request.getGame());
	}
	
	private void initializeTeam() {
		team = game.getTeams().findTeamByName(request.getTeam());
	}
	
	private boolean playerAlreadyJoinedATeam() {
		return game.getTeams().findTeamOfPlayer(request.getPlayer()) != null;
	}
	
	private boolean teamIsAlreadyFull() {
		return team.getMaximumSize() == team.size();
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.JOIN_TEAM);
	}
	
	private int getTeamColor() {
		return team.getColor().getRGB();
	}
	
	private void joinTeam() {
		team.addPlayer(request.getPlayer());
	}
	
	private boolean noSuchGame() {
		return !gameGateway.containsGame(request.getGame());
	}
	
	private boolean noSuchTeam() {
		return !game.getTeams().containsTeamWithName(request.getTeam());
	}
	
	private String getNameOfPlayer() {
		return playerGateway.findPlayerNameById(request.getPlayer());
	}
	
	private List<UUID> getPlayers() {
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
