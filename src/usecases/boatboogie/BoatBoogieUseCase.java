package usecases.boatboogie;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Location;
import game.Game;
import gateways.GameGateway;
import gateways.PlayerGateway;

public class BoatBoogieUseCase implements BoatBoogie {

	private BoatBoogieRequest request;
	private BoatBoogieResponse response;
	private PlayerGateway playerGateway;
	private GameGateway gameGateway;
	
	@Override
	public void execute(BoatBoogieRequest request, BoatBoogieResponse response) {
		setRequest(request);
		setResponse(response);
		
		Game game = getGameGateway().findGameByName(getRequest().getGameName());
		if (game == null)
			return;
		
		BoatBoogieResponseModel responseModel = new BoatBoogieResponseModel();
		responseModel.setPlayers(game.getUniquePlayerIds());
		responseModel.setWorldName(game.getVillagerSpawner().getVillagerSpawnLocation().getWorld().getName());
		
		List<Location> locations = new ArrayList<Location>();
		for (UUID uniquePlayerId : responseModel.getPlayers()) {
			locations.add(getPlayerGateway().findLocationOfPlayer(uniquePlayerId));
		}
		responseModel.setLocations(locations);
		
		
		getResponse().onPresentBoats(responseModel);
	}

	private BoatBoogieRequest getRequest() {
		return request;
	}

	private void setRequest(BoatBoogieRequest request) {
		this.request = request;
	}

	private BoatBoogieResponse getResponse() {
		return response;
	}

	private void setResponse(BoatBoogieResponse response) {
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
