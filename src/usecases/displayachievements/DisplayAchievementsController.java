package usecases.displayachievements;

import java.util.UUID;

import context.Context;

public class DisplayAchievementsController {
	
	public void onDisplayAchievements(UUID uniquePlayerId) {
		DisplayAchievementsView view = new DisplayAchievementsViewImpl(uniquePlayerId);
		DisplayAchievementsPresenter presenter = new DisplayAchievementsPresenter(view);
		DisplayAchievements useCase = new DisplayAchievementsUseCase();
		DisplayAchievementsRequestModel requestModel = new DisplayAchievementsRequestModel();
		requestModel.setUniquePlayerId(uniquePlayerId);
		useCase.setAchievementSystem(Context.achievementSystem);
		useCase.setGameStatisticsGateway(Context.gameStatisticGateway);
		useCase.setPermissionGateway(Context.permissionGateway);
		useCase.execute(requestModel, presenter);
	}

}
