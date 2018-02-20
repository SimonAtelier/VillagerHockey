package usecases.executecommand;

import usecases.executecommand.ExecuteCommand.ExecuteCommandResponse;

public class ExecuteCommandPresenter implements ExecuteCommandResponse {

	private ExecuteCommandView view;
	
	public ExecuteCommandPresenter(ExecuteCommandView view) {
		this.view = view;
	}
	
	@Override
	public void onToManyArguments(String name, String syntax) {
		view.displayToManyArguments(name, syntax);
	}

	@Override
	public void onMissingArguments(String name, String syntax) {
		view.displayMissingArguments(name, syntax);
	}

	@Override
	public void onUnknownCommand(String name) {
		view.displayUnknownCommand(name);
	}

}
