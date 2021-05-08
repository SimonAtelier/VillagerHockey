package usecases.addteam;

import java.util.ArrayList;
import java.util.List;

import entities.Team;
import entities.TeamColor;
import entities.Teams;
import game.Game;
import gateways.GameGateway;
import gateways.PermissionGateway;
import gateways.Permissions;

public class AddTeamUseCase implements AddTeam {

	private static final int MAXIMUM_AMOUNT_OF_TEAMS = 2;
	
	private Game game;
	private Teams teams;
	private AddTeamRequest request;
	private AddTeamResponse response;
	private GameGateway gameGateway;
	private PermissionGateway permissionGateway;

	@Override
	public void execute(AddTeamRequest request, AddTeamResponse response) {
		setRequest(request);
		setResponse(response);

		if (noPermission()) {
			sendNoPermissionResponse();
			return;
		}
		
		if (noSuchGame()) {
			sendNoSuchGameResponse();
			return;
		}
		
		initializeGame();
		initializeTeams();
		
		if (teamNameIsInvalid()) {
			sendTeamNameIsInvalidResponse();
			return;
		}

		if (teamWithNameExists()) {
			sendTeamWithNameAlreadyExistsResponse();
			return;
		}
		
		if (parseTeamColor() == null) {
			sendInvalidTeamColorResponse();
			return;
		}
		
		if (teamWithColorExists()) {
			sendTeamWithColorAlreadyExistsResponse();
			return;
		}
		
		if (maximumAmountReached()) {
			sendMaximumAmountOfTeamsAlreadyReachedResponse();
			return;
		}
		
		addTeamToGame();
		sendTeamSuccessfullyAddedResponse();
	}
	
	private void sendTeamNameIsInvalidResponse() {
		getResponse().onInvalidTeamName(request.getName() == null ? "null" : request.getName());
	}
	
	private void sendMaximumAmountOfTeamsAlreadyReachedResponse() {
		getResponse().onMaximumAmountOfTeamsAlreadyReached(MAXIMUM_AMOUNT_OF_TEAMS);
	}
	
	private void sendTeamSuccessfullyAddedResponse() {
		getResponse().onTeamSuccessfullyAdded(getRequest().getGame(), getRequest().getName());
	}
	
	private void sendTeamWithColorAlreadyExistsResponse() {
		getResponse().onTeamWithColorAlreadyExists(getRequest().getColor());
	}
	
	private void sendInvalidTeamColorResponse() {
		getResponse().onTeamColorIsNotValid(getRequest().getColor(), getPossibleTeamColorValues());
	}
	
	private void sendTeamWithNameAlreadyExistsResponse() {
		getResponse().onTeamWithNameAlreadyExists(getRequest().getName());
	}

	private void sendNoPermissionResponse() {
		getResponse().onNoPermission();
	}
	
	private void sendNoSuchGameResponse() {
		getResponse().onNoSuchGame(getRequest().getGame());
	}
	
	private void initializeGame() {
		game = gameGateway.findGameByName(getRequest().getGame());
	}
	
	private void initializeTeams() {
		teams = game.getTeams();
	}
	
	private boolean maximumAmountReached() {
		return game.getTeams().getNumberOfTeams() == MAXIMUM_AMOUNT_OF_TEAMS;
	}
	
	private List<String> getPossibleTeamColorValues() {
		List<String> values = new ArrayList<String>();
		for (TeamColor color : TeamColor.values()) {
			values.add(color + "");
		}
		return values;
	}
	
	private void addTeamToGame() {
		teams.add(new Team(request.getName(), parseTeamColor()));
	}
	
	private TeamColor parseTeamColor() {
		try {
			return TeamColor.valueOf(request.getColor());
		} catch (Exception e) {
			return null;
		}
	}
	
	private boolean teamNameIsInvalid() {
		return getRequest().getName() == null || getRequest().getName().trim().isEmpty();
	}

	private boolean teamWithNameExists() {
		return teams.containsTeamWithName(getRequest().getName());
	}
	
	private boolean teamWithColorExists() {
		return teams.containsTeamWithColor(parseTeamColor());
	}

	private boolean noSuchGame() {
		return !gameGateway.containsGame(getRequest().getGame());
	}

	private boolean noPermission() {
		return !permissionGateway.hasPermission(getRequest().getPlayer(), Permissions.ADD_TEAM);
	}
	
	private AddTeamResponse getResponse() {
		return response;
	}
	
	private void setResponse(AddTeamResponse response) {
		this.response = response;
	}
	
	private AddTeamRequest getRequest() {
		return request;
	}

	private void setRequest(AddTeamRequest request) {
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
	
}
