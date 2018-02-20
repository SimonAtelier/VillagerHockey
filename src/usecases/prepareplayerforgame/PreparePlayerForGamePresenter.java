package usecases.prepareplayerforgame;

import java.util.UUID;

import usecases.prepareplayerforgame.PreparePlayerForGame.PreparePlayerForGameResponse;

public class PreparePlayerForGamePresenter implements PreparePlayerForGameResponse {

	private PreparePlayerForGameView view;
	
	public PreparePlayerForGamePresenter(PreparePlayerForGameView view) {
		this.view = view;
	}
	
	@Override
	public void presentHockeySticks(UUID uniquePlayerId) {
		view.displayHockeySticks(uniquePlayerId);
	}

	@Override
	public void presentScores(UUID uniquePlayerId) {
		view.displayScores(uniquePlayerId);
	}

}
