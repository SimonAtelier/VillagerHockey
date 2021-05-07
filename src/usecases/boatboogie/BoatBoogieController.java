package usecases.boatboogie;

import context.Context;

public class BoatBoogieController {
	
	public void onBoatBoogie(String gameName) {
		BoatBoogiePresenter presenter = new BoatBoogiePresenter();
		BoatBoogie useCase = new BoatBoogieUseCase();
		BoatBoogieRequestModel requestModel = new BoatBoogieRequestModel();
		requestModel.setGameName(gameName);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(requestModel, presenter);
	}

}
