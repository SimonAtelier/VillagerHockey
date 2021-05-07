package usecases.spawnvillager;

import entities.config.Configuration;
import game.Game;
import game.VillagerSpawner;
import gateways.GameGateway;

public class SpawnVillagerUseCase implements SpawnVillager {

	private Game game;
	private SpawnVillagerRequest request;
	private SpawnVillagerResponse response;
	private GameGateway gameGateway;
	private Configuration configuration;
	
	@Override
	public void execute(SpawnVillagerRequest request, SpawnVillagerResponse response) {
		setRequest(request);
		setResponse(response);
		
		if (!findGame())
			return;
		
		spawnRegular();
	}
	
	private boolean findGame() {
		game = getGameGateway().findGameByName(getRequest().getGame());
		return game != null;
	}
	
	private void spawnPinata() {
		VillagerSpawner villagerSpawner = game.getVillagerSpawner();
		villagerSpawner.setPassenger(true);
		villagerSpawner.setAIEnabled(getConfiguration().isVillagerAIEnabled());
		villagerSpawner.spawnVillager();
		villagerSpawner.setCustomName("Dinnerbone");
		villagerSpawner.setCustomPassengerName("Arriba");
	}
	
	private void spawnRegular() {
		VillagerSpawner villagerSpawner = game.getVillagerSpawner();
		villagerSpawner.setPassenger(getRequest().isPassenger());
		villagerSpawner.setAIEnabled(getConfiguration().isVillagerAIEnabled());
		villagerSpawner.spawnVillager();
		if (request.isPassenger())
			villagerSpawner.setCustomPassengerName(getRequest().getPassengerName());
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
	
	private Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
