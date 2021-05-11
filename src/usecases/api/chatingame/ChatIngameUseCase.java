package usecases.api.chatingame;

import java.util.List;
import java.util.UUID;

import entities.Team;
import entities.Teams;
import entities.config.Configuration;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;
import gateways.PlayerGateway;

public class ChatIngameUseCase implements ChatIngame {
	
	private String chatWithAllLabel;
	private Team team;
	private Game game;
	private ChatIngameRequest request;
	private Configuration configuration;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;
	private PlayerGateway playerGateway;
	
	@Override
	public void execute(ChatIngameRequest request, ChatIngameResponse response) {
		setChatWithAllLabelFromConfiguration();
		setRequest(request);
		
		if (noPermission()) {
			response.onNoPermission();
			return;
		}
		
		findGame();
		findTeam();
		
		if (shouldChatWithAll()) {
			response.onChatWithAll(findAllPlayers(), getMessageWithoutChatWithAllPrefix(), getNameOfPlayer());
			return;
		}
		
		if (playerIsNotInATeam()) {
			response.onPlayerHasNoTeam();
			return;
		}
		
		response.onChatWithTeam(findPlayersOfTeam(), request.getMessage(), getNameOfPlayer());
	}
	
	private String getMessageWithoutChatWithAllPrefix() {
		String message = request.getMessage();
		message = message.replaceFirst(chatWithAllLabel, "");
		return message;
	}
	
	private boolean shouldChatWithAll() {
		return request.getMessage().startsWith(chatWithAllLabel);
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
	
	private List<UUID> findAllPlayers() {
		return game.getUniquePlayerIds();
	}
	
	private boolean playerIsNotInATeam() {
		return team == null;
	}
	
	private boolean noPermission() {
		return !permissionGateway.hasPermission(request.getPlayer(), Permissions.CHAT_INGAME);
	}
	
	private void setChatWithAllLabelFromConfiguration() {
		chatWithAllLabel = configuration.getChatWithAllLabel();
	}
	
	private void setRequest(ChatIngameRequest request) {
		this.request = request;
	}
	
	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
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
