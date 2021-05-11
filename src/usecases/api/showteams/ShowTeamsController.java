package usecases.api.showteams;

import java.util.UUID;

import context.Context;
import usecases.api.selectteam.SelectTeamController;
import usecases.api.selectteam.SelectTeamView;
import usecases.api.selectteam.SelectTeamViewImpl;
import usecases.api.showteams.ShowTeams.ShowTeamsResponse;

public class ShowTeamsController {

	public void onShowTeams(UUID uniquePlayerId) {
		ShowTeamsRequestModel requestModel = new ShowTeamsRequestModel();
		requestModel.setPlayer(uniquePlayerId);
		SelectTeamView view = new SelectTeamViewImpl(uniquePlayerId);
		view.setTeamSelectListener(new SelectTeamController());
		ShowTeamsResponse presenter = new ShowTeamsPresenter(view);
		ShowTeams useCase = new ShowTeamsUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(requestModel, presenter);
	}
	
}
