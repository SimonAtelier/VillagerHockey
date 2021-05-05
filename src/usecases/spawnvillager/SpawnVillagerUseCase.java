package usecases.spawnvillager;

import game.Game;
import game.VillagerSpawner;
import gateways.GameGateway;

public class SpawnVillagerUseCase implements SpawnVillager {

	private Game game;
	private SpawnVillagerRequest request;
	private SpawnVillagerResponse response;
	private GameGateway gameGateway;

	@Override
	public void execute(SpawnVillagerRequest request, SpawnVillagerResponse response) {
		setRequest(request);
		setResponse(response);
		
		if (!findGame())
			return;
		
		spawn();
		sendSpecialRoundReponse();
	}
	
	private void sendSpecialRoundReponse() {
		getResponse().onSpecialRound(game.getUniquePlayerIds());
	}
	
	private boolean findGame() {
		game = getGameGateway().findGameByName(getRequest().getGame());
		return game != null;
	}
	
	private void spawn() {
		VillagerSpawner villagerSpawner = game.getVillagerSpawner();
		villagerSpawner.setPassenger(true);
		villagerSpawner.setAIEnabled(true);
		villagerSpawner.spawnVillager();
	}

	private SpawnVillagerRequest getRequest() {
		return request;
	}

	private void setRequest(SpawnVillagerRequest request) {
		this.request = request;
	}

	private SpawnVillagerResponse getResponse() {
		return response;
	}

	private void setResponse(SpawnVillagerResponse response) {
		this.response = response;
	}
	
	private GameGateway getGameGateway() {
		return gameGateway;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
