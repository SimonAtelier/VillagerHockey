package usecases.unlockachievement;

import java.util.UUID;

import context.Context;

public class UnlockAchievementController {

	public void onUnlockAchievement(UUID uniquePlayerId, String achievementId) {
		UnlockAchievementView view = new UnlockAchievementViewImpl(uniquePlayerId);
		UnlockAchievementPresenter presenter = new UnlockAchievementPresenter(view);
		UnlockAchievementRequestModel requestModel = new UnlockAchievementRequestModel();
		UnlockAchievement useCase = new UnlockAchievementUseCase();
		useCase.setAchievementSystem(Context.achievementSystem);
		useCase.setConfiguration(Context.configuration);
		useCase.setGameSatisticGateway(Context.gameStatisticGateway);
		requestModel.setUniquePlayerId(uniquePlayerId);
		requestModel.setAchievementId(achievementId);
		useCase.execute(requestModel, presenter);
	}
	
}
