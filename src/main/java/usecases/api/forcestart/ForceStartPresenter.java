package usecases.api.forcestart;

import usecases.api.forcestart.ForceStart.ForceStartResponse;

public class ForceStartPresenter implements ForceStartResponse {

	private ForceStartView view;
	
	public ForceStartPresenter(ForceStartView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void displayForcedStart(String game) {
		view.displayForcedStart(game);
	}

}
