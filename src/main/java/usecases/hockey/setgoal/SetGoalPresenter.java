package usecases.hockey.setgoal;

import usecases.hockey.setgoal.SetGoal.SetGoalResponse;

public class SetGoalPresenter implements SetGoalResponse {

	private SetGoalView view;
	
	public SetGoalPresenter(SetGoalView view) {
		this.view = view;
	}
	
	@Override
	public void onNoSuchGame(String name) {
		view.displayNoSuchGame(name);
	}

	@Override
	public void onNoSuchTeam(String name) {
		view.displayNoSuchTeam(name);
	}

	@Override
	public void onInvalidLocationId(String id) {
		view.displayInvalidLocationId(id);
	}

	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onGoalLocationSuccessfullySet(String locationId) {
		view.displayGoalLocationSuccessfullySet(locationId);
	}

	@Override
	public void onSetLocationWithIdFirst(String id) {
		view.displayNeedToSetLocationWithIdFirst(id);
	}

}
