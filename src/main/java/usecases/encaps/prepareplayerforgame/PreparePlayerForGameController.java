package usecases.encaps.prepareplayerforgame;

import java.util.UUID;

import context.Context;
import usecases.encaps.prepareplayerforgame.PreparePlayerForGame.PreparePlayerForGameResponse;

public class PreparePlayerForGameController {

	public void onPreparePlayersForGame(String gameName, UUID uniquePlayerId) {
		PreparePlayerForGameView view = new PreparePlayerForGameViewImpl();
		PreparePlayerForGameResponse presenter = new PreparePlayerForGamePresenter(view);
		PreparePlayerForGame useCase = new PreparePlayerForGameUseCase();
		PreparePlayerForGameRequestModel requestModel = new PreparePlayerForGameRequestModel();
		requestModel.setGameName(gameName);
		requestModel.setUniquePlayerId(uniquePlayerId);
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(requestModel, presenter);
	}
	
}
