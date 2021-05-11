package usecases.encaps.displaywinner;

import context.Context;
import usecases.encaps.displaywinner.DisplayWinner.DisplayWinnerRequest;
import usecases.encaps.displaywinner.DisplayWinner.DisplayWinnerResponse;

public class DisplayWinnerController {

	public void onDisplayWinner(String game) {
		DisplayWinnerView view = new DisplayWinnerViewImpl();
		DisplayWinnerResponse presenter = new DisplayWinnerPresenter(view);
		DisplayWinner useCase = new DisplayWinnerUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(createRequest(game), presenter);
	}
	
	private DisplayWinnerRequest createRequest(String game) {
		DisplayWinnerRequestModel requestModel = new DisplayWinnerRequestModel();
		requestModel.setGame(game);
		return requestModel;
	}
	
}
