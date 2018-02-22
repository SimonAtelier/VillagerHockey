package usecases.executecommand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import usecases.executecommand.ExecuteCommand.ExecuteCommandResponse;

public class ExecuteCommandController {

	private ExecuteCommand useCase;
	private ExecuteCommandResponse presenter;
	
	public ExecuteCommandController(ExecuteCommand useCase, ExecuteCommandResponse presenter) {
		this.useCase = useCase;
		this.presenter = presenter;
	}
	
	private List<String> createArgumentsList(String[] arguments) {
		List<String> argumentList = new ArrayList<String>();
		for (int i = 2; i < arguments.length; i++) {
			argumentList.add(arguments[i]);
		}
		return argumentList;
	}

	public void handleRequest(UUID player, String[] arguments) {
		if (arguments.length == 0)
			return;
		
		if (!arguments[0].equalsIgnoreCase(RootCommandLabel.ROOT_COMMAND_LABEL))
			return;
		
		String name;
		List<String> argumentsList;

		if (arguments.length < 2) {
			name = "";
			argumentsList = new ArrayList<String>();
		} else {
			name = arguments[1];
			argumentsList = createArgumentsList(arguments);
		}
		
		useCase.execute(player, name, argumentsList, presenter);
	}
	
}
