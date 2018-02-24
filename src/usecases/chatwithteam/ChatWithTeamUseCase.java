package usecases.chatwithteam;

import java.util.List;
import java.util.UUID;

import entities.Team;
import entities.Teams;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class ChatWithTeamUseCase implements ChatWithTeam {

	private Team team;
	private Game game;
	private ChatWithTeamRequest request;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(ChatWithTeamRequest request, ChatWithTeamResponse response) {
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		findGame();
		findTeam();
		
		if (playerIsNotInATeam()) {
			response.onPlayerHasNoTeam();
			return;
		}
	
		response.onChatWithTeam(findPlayersOfTeam(), request.getMessage(), getNameOfPlayer());
	}
	
	private String getNameOfPlayer() {
		return playerGateway.findPlayerNameById(request.getPlayer());
	}
	
	private void findGame() {
		game = gameGateway.findGameOfPlayer(request.getPlayer());
	}
	
	private void findTeam() {
		Teams teams = game.getTeams();
		team = teams.findTeamOfPlayer(request.getPlayer());
	}
	
	private List<UUID> findPlayersOfTeam() {
		return team.getPlayers();
	}
	
	private boolean playerIsNotInATeam() {
		return team == null;
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.CHAT_INGAME);
	}
	
	private void setRequest(ChatWithTeamRequest request) {
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
