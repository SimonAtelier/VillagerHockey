package usecases.spawnvillager;

import context.Context;

public class SpawnVillagerController {

	public void onSpawnVillager(String game) {
		SpawnVillagerPresenter presenter = new SpawnVillagerPresenter();
		SpawnVillager useCase = new SpawnVillagerUseCase();
		SpawnVillagerRequestModel requestModel = new SpawnVillagerRequestModel();
		requestModel.setGame(game);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setConfiguration(Context.configuration);
		useCase.execute(requestModel, presenter);
	}
	
}
