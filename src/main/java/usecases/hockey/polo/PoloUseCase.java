package usecases.hockey.polo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Location;
import entities.TeamColor;
import game.Game;
import gateways.GameGateway;
import gateways.PlayerGateway;

public class PoloUseCase implements Polo {

	private PoloRequest request;
	private PoloResponse response;
	private PlayerGateway playerGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(PoloRequest request, PoloResponse response) {
		setRequest(request);
		setResponse(response);
		
		Game game = getGameGateway().findGameByName(getRequest().getGameName());
		if (game == null)
			return;
		
		PoloResponseModel responseModel = new PoloResponseModel();
		responseModel.setPlayers(game.getUniquePlayerIds());
		
		List<TeamColor> teamColors = new ArrayList<TeamColor>();
		List<Location> locations = new ArrayList<Location>();
		for (UUID uniquePlayerId : responseModel.getPlayers()) {
			locations.add(getPlayerGateway().findLocationOfPlayer(uniquePlayerId));
			teamColors.add(game.getTeams().findTeamOfPlayer(uniquePlayerId).getColor());
		}
		responseModel.setLocations(locations);
		responseModel.setTeamColors(teamColors);
		
		getResponse().onPresent(responseModel);
	}

	private PoloRequest getRequest() {
		return request;
	}

	private void setRequest(PoloRequest request) {
		this.request = request;
	}

	private PoloResponse getResponse() {
		return response;
	}

	private void setResponse(PoloResponse response) {
		this.response = response;
	}
	
	private PlayerGateway getPlayerGateway() {
		return playerGateway;
	}

	@Override
	public void setPlayerGateway(PlayerGateway playerGateway) {
		this.playerGateway = playerGateway;
	}

	private GameGateway getGameGateway() {
		return gameGateway;
	}
	
	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
