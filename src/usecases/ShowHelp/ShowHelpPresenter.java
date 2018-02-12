package usecases.ShowHelp;

import java.util.List;

import command.Command;
import usecases.ShowHelp.ShowHelp.ShowHelpResponse;

public class ShowHelpPresenter implements ShowHelpResponse {

	private ShowHelpView view;
	
	public ShowHelpPresenter(ShowHelpView view) {
		this.view = view;
	}
	
	@Override
	public void onNoPermission() {
		view.displayNoPermission();
	}

	@Override
	public void presentHelp(List<Command> commands) {
		StringBuffer buffer = new StringBuffer();
		for (Command command : commands) {
			buffer.append(command.getSyntax());
			buffer.append("\n");
		}
		view.displayHelp(buffer.toString());
	}

}
