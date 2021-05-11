package usecases.api.executenonegamecommand;

import usecases.api.executenonegamecommand.ExecuteNoneGameCommand.ExecuteNoneGameCommandResponse;

public class ExecuteNoneGameCommandPresenter implements ExecuteNoneGameCommandResponse {

	private ExecuteNoneGameCommandView view;
	
	public ExecuteNoneGameCommandPresenter(ExecuteNoneGameCommandView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

}
