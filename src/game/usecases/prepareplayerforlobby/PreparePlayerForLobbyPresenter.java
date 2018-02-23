package game.usecases.prepareplayerforlobby;

import game.usecases.prepareplayerforlobby.PreparePlayerForLobby.PreparePlayerForLobbyResponse;

public class PreparePlayerForLobbyPresenter implements PreparePlayerForLobbyResponse {

	private LobbyView view;
	
	public PreparePlayerForLobbyPresenter(LobbyView view) {
		this.view = view;
	}
	
	@Override
	public void present(PreparePlayerForLobbyResponseModel responseModel) {
		if (responseModel.isCanForceStart())
			view.displayForceStart();
		
		if (responseModel.isCanSelectTeam())
			view.displaySelectTeam();
		
		if (responseModel.isCanViewAchievements())
			view.displayAchievements();
		
		view.displayLeaveGame();
		
		if (responseModel.isMaxHealth())
			view.displayMaxHealth();

		view.displayFoodLevel(responseModel.getFoodLevel());
		view.displayGameMode(responseModel.getGameMode());
		view.displayLevel(responseModel.getLevel());
	}

}
