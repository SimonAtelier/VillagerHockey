package usecases.polo;

import context.Context;

public class PoloController {

	public void onPolo(String gameName) {
		PoloPresenter presenter = new PoloPresenter();
		PoloUseCase useCase = new PoloUseCase();
		PoloRequestModel requestModel = new PoloRequestModel();
		requestModel.setGameName(gameName);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setPlayerGateway(Context.playerGateway);
		useCase.execute(requestModel, presenter);
	}
	
}
