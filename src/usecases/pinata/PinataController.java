package usecases.pinata;

public class PinataController {

	public void onPinata(String gameName) {
		PinataPresenter presenter = new PinataPresenter();
		Pinata useCase = new PinataUseCase();
		PinataRequestModel requestModel = new PinataRequestModel();
		requestModel.setGameName(gameName);
		useCase.execute(requestModel, presenter);
	}
	
}
