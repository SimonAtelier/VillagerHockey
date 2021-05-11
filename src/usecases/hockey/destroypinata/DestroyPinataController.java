package usecases.hockey.destroypinata;

import java.util.UUID;

import context.Context;

public class DestroyPinataController {

	public void onDestroyPinata(UUID uniquePlayerId) {
		DestroyPinataPresenter presenter = new DestroyPinataPresenter();
		DestroyPinata useCase = new DestroyPinataUseCase();
		DestroyPinataRequestModel requestModel = new DestroyPinataRequestModel();
		requestModel.setDestroyer(uniquePlayerId);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.setGameStatisticGateway(Context.gameStatisticGateway);
		useCase.execute(requestModel, presenter);
	}
	
}
