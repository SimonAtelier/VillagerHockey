package usecases.PerformAction.PlaceJoinSign;

import usecases.PerformAction.PlaceJoinSign.PlaceJoinSign.PlaceJoinSignResponse;

public class PlaceJoinSignPresenter implements PlaceJoinSignResponse {

	private PlaceJoinSignView view;
	
	public PlaceJoinSignPresenter(PlaceJoinSignView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onNoSuchGame(String game) {
		view.displayNoSuchGame(game);
	}

	@Override
	public void onJoinSignSuccessfullySet(String game, ResponseModel responseModel) {
		view.displayJoinSignSuccessfullySet(game, responseModel);
	}

}
