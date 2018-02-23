package game.usecases.prepareplayerforlobby;

import game.usecases.prepareplayerforlobby.PreparePlayerForLobby.PreparePlayerForLobbyResponse;

public class PreparePlayerForLobbyPresenter implements PreparePlayerForLobbyResponse {

	private LobbyMenuView view;
	
	public PreparePlayerForLobbyPresenter(LobbyMenuView view) {
		this.view = view;
	}
	
	@Override
	public void present(PreparePlayerForLobbyResponseModel responseModel) {
		view.displayLeaveGame();
		view.displayAchievements();
		
		if (responseModel.isCanForceStart()) {
			view.displayForceStart();
		}
		
		if (responseModel.isCanSelectTeam()) {
			view.displaySelectTeam();
		}
	}

}
