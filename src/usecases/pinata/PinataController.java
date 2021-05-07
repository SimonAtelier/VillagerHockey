package usecases.pinata;

import context.Context;

public class PinataController {

	public void onPinata(String gameName) {
		PinataPresenter presenter = new PinataPresenter();
		PinataRequestModel requestModel = new PinataRequestModel();
		Pinata useCase = new PinataUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		requestModel.setGameName(gameName);
		useCase.execute(requestModel, presenter);
	}
	
}
