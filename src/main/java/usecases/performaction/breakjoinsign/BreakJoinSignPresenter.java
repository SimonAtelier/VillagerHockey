package usecases.performaction.breakjoinsign;

import usecases.performaction.breakjoinsign.BreakJoinSign.BreakJoinSignResponse;

public class BreakJoinSignPresenter implements BreakJoinSignResponse {

	private BreakJoinSignView view;
	
	public BreakJoinSignPresenter(BreakJoinSignView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void onDiscardBreaking() {
		view.discardBreaking();
	}

	@Override
	public void onNoSuchGame(String game) {
		view.displayNoSuchGame(game);
	}

	@Override
	public void onSuccessfullyRemoved(String game) {
		view.displaySuccessfullyRemove(game);
	}

}
