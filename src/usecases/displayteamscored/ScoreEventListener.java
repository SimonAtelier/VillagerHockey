package usecases.displayteamscored;

import context.Context;
import game.event.TeamScoreListener;
import usecases.displayteamscored.DisplayTeamScored.DisplayTeamScoredResponse;

public class ScoreEventListener implements TeamScoreListener {

	@Override
	public void onTeamScored(String game, String team) {
		DisplayTeamScoreRequestModel requestModel = new DisplayTeamScoreRequestModel();
		requestModel.setGame(game);
		requestModel.setTeam(team);
		
		DisplayTeamScoredView view = new DisplayTeamScoredViewImpl();
		DisplayTeamScoredResponse presenter = new DisplayTeamScoredPresenter(view);
		DisplayTeamScored useCase = new DisplayTeamScoredUseCase();
		useCase.setGameGateway(Context.gameGateway);
		useCase.execute(requestModel, presenter);
	}
	
}
