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
		
		if (isSpecialRound()) {
//			game.setGoalsEnabled(false);
//			spawnPinata();
//			sendSpecialRoundReponse();
//			return;
		}
				
		game.setGoalsEnabled(true);
		spawnRegular();
	}
		
	private boolean isSpecialRound() {
//		return (int) (Math.random() * 20) == 0;
		return true;
	}
	
	private void sendSpecialRoundReponse() {
		getResponse().onSpecialRound(game.getUniquePlayerIds());
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
		villagerSpawner.setPassenger(false);
		villagerSpawner.setAIEnabled(getConfiguration().isVillagerAIEnabled());
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
	
	private Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
