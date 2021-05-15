package usecases.hockey.updatestatistics;

import context.Context;

public class UpdateStatisticsController {
	
	public void onUpdate(String gameName) {
		UpdateStatisticsUseCase useCase = new UpdateStatisticsUseCase();
		UpdateStatisticsRequestModel request = new UpdateStatisticsRequestModel();
		request.setGame(gameName);
		useCase.setGameGateway(Context.gameGateway);
		useCase.setGameStatisticGateway(Context.gameStatisticGateway);
		useCase.execute(request);
	}

}
